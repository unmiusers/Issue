package com.project.issuetracker.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Wiki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "wiki_permissions",
            joinColumns = @JoinColumn(name = "wiki_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> permittedUsers = new HashSet<>();

    // Constructors, getters, and setters
    public Wiki() {
    }

    public Wiki(String title, String content, Issue issue, User user) {
        this.title = title;
        this.content = content;
        this.issue = issue;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getPermittedUsers() {
        return permittedUsers;
    }

    public void setPermittedUsers(Set<User> permittedUsers) {
        this.permittedUsers = permittedUsers;
    }
}
