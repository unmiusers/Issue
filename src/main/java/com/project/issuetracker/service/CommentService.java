package com.project.issuetracker.service;

import com.project.issuetracker.model.Comment;
import com.project.issuetracker.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByIssueId(Long issueId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getIssue().getId().equals(issueId))
                .toList();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public String getCommentContent(Long id) {
        return commentRepository.findById(id)
                .map(Comment::getContent)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public void setCommentContent(Long id, String content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(content);
        commentRepository.save(comment);
    }
}