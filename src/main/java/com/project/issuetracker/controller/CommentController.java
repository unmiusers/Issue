package com.project.issuetracker.controller;

import com.project.issuetracker.model.Comment;
import com.project.issuetracker.model.ApiResponse;
import com.project.issuetracker.service.CommentService;
import com.project.issuetracker.service.IssueService;
import com.project.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> addComment(@RequestBody Comment comment, @RequestParam Long issueId, @RequestParam Long userId) {
        comment.setIssue(issueService.getIssueById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found")));
        comment.setUser(userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));

        Comment createdComment = commentService.saveComment(comment);

        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Comment added successfully");
        response.setData(createdComment);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<ApiResponse> getCommentsByIssueId(@PathVariable Long issueId) {
        List<Comment> comments = commentService.getCommentsByIssueId(issueId);

        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Comments retrieved successfully");
        response.setData(comments);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Comment retrieved successfully");
        response.setData(comment);

        return ResponseEntity.ok(response);
    }
}