package com.project.issuetracker.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    // Constructors, getters, and setters
    public Issue() {
    }

    public Issue(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

}
