package com.project.issuetracker.controller;

import com.project.issuetracker.model.ApiResponse;
import com.project.issuetracker.model.Wiki;
import com.project.issuetracker.service.IssueService;
import com.project.issuetracker.service.UserService;
import com.project.issuetracker.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wikis")
public class WikiController {

    @Autowired
    private WikiService wikiService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createWiki(@RequestBody Wiki wiki, @RequestParam Long issueId, @RequestParam Long userId) {
        wiki.setIssue(issueService.getIssueById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found")));
        wiki.setUser(userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));

        Wiki createdWiki = wikiService.createWiki(wiki);
        return ResponseEntity.ok(new ApiResponse(true, "Wiki created successfully", createdWiki));
    }

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<?> getWikisByIssueId(@PathVariable Long issueId) {
        List<Wiki> wikis = wikiService.getWikisByIssueId(issueId);
        return ResponseEntity.ok(wikis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWikiById(@PathVariable Long id) {
        Wiki wiki = wikiService.getWikiById(id)
                .orElseThrow(() -> new RuntimeException("Wiki not found"));
        return ResponseEntity.ok(wiki);
    }
}