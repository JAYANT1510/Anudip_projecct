package com.lms.service;

import com.lms.exception.ResourceNotFoundException;
import com.lms.model.Course;
import com.lms.model.Lesson;
import com.lms.repository.CourseRepository;
import com.lms.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Lesson> getLessonsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        return lessonRepository.findByCourse(course);
    }

    public Lesson createLesson(Long courseId, Lesson lesson) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }
}
