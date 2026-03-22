package com.lms.controller;

import com.lms.model.User;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return userService.getUserByUsername(user.getUsername())
                .filter(u -> u.getPassword().equals(u.getPassword())) // Plain text for simplicity, use hashing in production
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.status(401).build());
    }
}
