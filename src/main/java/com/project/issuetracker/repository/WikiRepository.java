package com.project.issuetracker.repository;

import com.project.issuetracker.model.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WikiRepository extends JpaRepository<Wiki, Long> {
    List<Wiki> findByIssueId(Long issueId);
}