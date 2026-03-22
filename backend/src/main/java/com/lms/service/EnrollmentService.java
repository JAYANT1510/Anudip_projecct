package com.lms.service;

import com.lms.exception.ResourceNotFoundException;
import com.lms.model.Course;
import com.lms.model.Enrollment;
import com.lms.model.User;
import com.lms.repository.CourseRepository;
import com.lms.repository.EnrollmentRepository;
import com.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Enrollment enrollUser(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        // Check if already enrolled
        if (enrollmentRepository.findByUserAndCourse(user, course).isPresent()) {
            throw new RuntimeException("User is already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment(user, course);
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getEnrollmentsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return enrollmentRepository.findByUser(user);
    }
}
