package com.project.issuetracker.controller;

import com.project.issuetracker.model.ApiResponse;
import com.project.issuetracker.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.issuetracker.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveIssue(@RequestBody Issue issue) {
        Issue savedIssue = issueService.saveIssue(issue);

        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Issue saved successfully");
        response.setData(savedIssue);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse> getIssuesByStatus(@PathVariable String status) {
        List<Issue> issues = issueService.getIssuesByStatus(status);

        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Issues retrieved successfully");
        response.setData(issues);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getIssueById(@PathVariable Long id) {
        Issue issue = issueService.getIssueById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Issue retrieved successfully");
        response.setData(issue);

        return ResponseEntity.ok(response);
    }
}
