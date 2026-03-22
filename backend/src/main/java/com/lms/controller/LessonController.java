package com.lms.controller;

import com.lms.model.Lesson;
import com.lms.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@CrossOrigin(origins = "http://localhost:3000")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Lesson>> getLessonsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(lessonService.getLessonsByCourse(courseId));
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Lesson> createLesson(@PathVariable Long courseId, @RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.createLesson(courseId, lesson));
    }
}
