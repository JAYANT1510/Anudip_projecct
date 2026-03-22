package com.lms.controller;

import com.lms.model.Enrollment;
import com.lms.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "http://localhost:3000")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll/{userId}/{courseId}")
    public ResponseEntity<Enrollment> enrollUser(@PathVariable Long userId, @PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.enrollUser(userId, courseId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByUser(userId));
    }
}
