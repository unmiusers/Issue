package com.project.issuetracker.controller;

import com.project.issuetracker.model.ApiResponse;
import com.project.issuetracker.model.User;
import com.project.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticateUser(user);
        if (isAuthenticated) {
            return ResponseEntity.ok(new ApiResponse(true, "User signed in successfully"));
        } else {
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid email or password"));
        }
    }
}
