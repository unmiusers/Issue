package com.project.issuetracker.service;

import com.project.issuetracker.model.User;
import com.project.issuetracker.model.Wiki;
import com.project.issuetracker.repository.WikiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WikiService {

    @Autowired
    private WikiRepository wikiRepository;

    public Wiki createWiki(Wiki wiki) {
        return wikiRepository.save(wiki);
    }

    public List<Wiki> getWikisByIssueId(Long issueId) {
        return wikiRepository.findByIssueId(issueId);
    }

    public Optional<Wiki> getWikiById(Long id) {
        return wikiRepository.findById(id);
    }

    public Wiki updateWiki(Wiki wiki) {
        return wikiRepository.save(wiki);
    }

    public boolean userHasPermission(Long wikiId, Long userId) {
        Optional<Wiki> wiki = wikiRepository.findById(wikiId);
        if (wiki.isPresent()) {
            Set<User> permittedUsers = wiki.get().getPermittedUsers();
            return permittedUsers.stream().anyMatch(user -> user.getId().equals(userId));
        }
        return false;
    }

    public void addUserPermission(Long wikiId, User user) {
        Optional<Wiki> wiki = wikiRepository.findById(wikiId);
        if (wiki.isPresent()) {
            wiki.get().getPermittedUsers().add(user);
            wikiRepository.save(wiki.get());
        }
    }
}
