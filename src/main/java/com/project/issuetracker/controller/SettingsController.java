package com.project.issuetracker.controller;

import com.project.issuetracker.model.User;
import com.project.issuetracker.model.ApiResponse;
import com.project.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> updateSettings(@RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(new ApiResponse(true, "Settings updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ApiResponse(false, "Failed to update settings: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(false, "User not found"));
        }
    }
}
