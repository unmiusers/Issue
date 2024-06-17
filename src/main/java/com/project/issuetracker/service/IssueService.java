package com.project.issuetracker.service;

import com.project.issuetracker.model.Comment;
import com.project.issuetracker.model.Issue;
import com.project.issuetracker.model.User;
import com.project.issuetracker.repository.CommentRepository;
import com.project.issuetracker.repository.IssueRepository;
import com.project.issuetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    public Issue saveIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public List<Issue> getIssuesByStatus(String status) {
        return issueRepository.findByStatus(status);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Optional<Issue> getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    public Issue createIssue(Issue issue) {
        issue.setStatus("New");
        return issueRepository.save(issue);
    }
}