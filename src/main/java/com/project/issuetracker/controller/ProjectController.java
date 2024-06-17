package com.project.issuetracker.controller;

import com.project.issuetracker.model.User;
import com.project.issuetracker.model.ApiResponse;
import com.project.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            return ResponseEntity.ok(new ApiResponse(true, "User saved successfully", savedUser));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ApiResponse(false, "Failed to save user: " + e.getMessage()));
        }
    }
}
