import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [enrolledCourseIds, setEnrolledCourseIds] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [activeTab, setActiveTab] = useState('all'); // 'all', 'my-courses', 'admin'
  const [selectedCourse, setSelectedCourse] = useState(null);
  const [lessons, setLessons] = useState([]);

  // Admin states
  const [newCourse, setNewCourse] = useState({ title: '', description: '', instructorId: 1 });

  const studentId = 2;

  useEffect(() => {
    fetchCourses();
    fetchEnrolledCourses();
  }, []);

  const fetchCourses = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/courses');
      setCourses(response.data);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching courses:", error);
      setLoading(false);
    }
  };

  const fetchEnrolledCourses = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/enrollments/user/${studentId}`);
      setEnrolledCourseIds(response.data.map(enroll => enroll.course.id));
    } catch (error) {
      console.error("Error fetching enrolled courses:", error);
    }
  };

  const fetchLessons = async (courseId) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/lessons/course/${courseId}`);
      setLessons(response.data);
    } catch (error) {
      console.error("Error fetching lessons:", error);
    }
  };

  const handleEnroll = async (courseId) => {
    try {
      await axios.post(`http://localhost:8080/api/enrollments/enroll/${studentId}/${courseId}`);
      alert("Enrolled successfully!");
      fetchEnrolledCourses();
    } catch (error) {
      alert(error.response?.data?.message || "Error enrolling in course");
    }
  };

  const handleAccessContent = (course) => {
    setSelectedCourse(course);
    fetchLessons(course.id);
  };

  const handleCreateCourse = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/courses', newCourse);
      alert("Course created successfully!");
      setNewCourse({ title: '', description: '', instructorId: 1 });
      fetchCourses();
    } catch (error) {
      alert("Error creating course");
    }
  };

  const handleDeleteCourse = async (id) => {
    if (window.confirm("Are you sure you want to delete this course?")) {
      try {
        await axios.delete(`http://localhost:8080/api/courses/${id}`);
        fetchCourses();
      } catch (error) {
        alert("Error deleting course");
      }
    }
  };

  const filteredCourses = courses.filter(course => {
    const matchesSearch = course.title.toLowerCase().includes(searchTerm.toLowerCase());
    if (activeTab === 'my-courses') return matchesSearch && enrolledCourseIds.includes(course.id);
    return matchesSearch;
  });

  if (selectedCourse) {
    return (
      <div className="App">
        <button className="back-btn" onClick={() => setSelectedCourse(null)}>← Back to Dashboard</button>
        <h1>{selectedCourse.title}</h1>
        <p>{selectedCourse.description}</p>
        <div className="lesson-list">
          <h2>Lessons</h2>
          {lessons.length > 0 ? (
            lessons.map(lesson => (
              <div key={lesson.id} className="lesson-item">
                <h4>{lesson.title}</h4>
                <p>{lesson.content}</p>
              </div>
            ))
          ) : (
            <p>No lessons available for this course yet.</p>
          )}
        </div>
      </div>
    );
  }

  return (
    <div className="App">
      <h1>Learning Management System</h1>
      
      <div className="tabs">
        <div className={`tab ${activeTab === 'all' ? 'active' : ''}`} onClick={() => setActiveTab('all')}>Explore</div>
        <div className={`tab ${activeTab === 'my-courses' ? 'active' : ''}`} onClick={() => setActiveTab('my-courses')}>My Courses</div>
        <div className={`tab ${activeTab === 'admin' ? 'active' : ''}`} onClick={() => setActiveTab('admin')}>Admin Panel</div>
      </div>

      {activeTab === 'admin' ? (
        <div className="admin-panel">
          <h2>Create New Course</h2>
          <form className="admin-form" onSubmit={handleCreateCourse}>
            <input 
              type="text" placeholder="Course Title" required
              value={newCourse.title} onChange={(e) => setNewCourse({...newCourse, title: e.target.value})}
            />
            <textarea 
              placeholder="Course Description" required
              value={newCourse.description} onChange={(e) => setNewCourse({...newCourse, description: e.target.value})}
            />
            <button className="enroll-btn" type="submit">Create Course</button>
          </form>

          <h2>Manage Existing Courses</h2>
          <div className="course-grid">
            {courses.map(course => (
              <div key={course.id} className="course-card">
                <button className="delete-btn" onClick={() => handleDeleteCourse(course.id)}>Delete</button>
                <h3>{course.title}</h3>
                <p>{course.description}</p>
              </div>
            ))}
          </div>
        </div>
      ) : (
        <>
          <input 
            type="text" className="search-bar" placeholder="Search courses..." 
            value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)}
          />
          <div className="course-grid">
            {filteredCourses.map(course => (
              <div key={course.id} className="course-card">
                {enrolledCourseIds.includes(course.id) && <span className="enrolled-badge">✓ Enrolled</span>}
                <h3>{course.title}</h3>
                <p>{course.description}</p>
                {enrolledCourseIds.includes(course.id) ? (
                  <button className="enroll-btn" onClick={() => handleAccessContent(course)}>Access Content</button>
                ) : (
                  <button className="enroll-btn" onClick={() => handleEnroll(course.id)}>Enroll Now</button>
                )}
              </div>
            ))}
          </div>
        </>
      )}
    </div>
  );
}

export default App;
