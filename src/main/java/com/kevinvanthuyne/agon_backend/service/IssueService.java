package com.kevinvanthuyne.agon_backend.service;

import com.kevinvanthuyne.agon_backend.dao.IssueDao;
import com.kevinvanthuyne.agon_backend.model.Issue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {
    private final IssueDao issueDao;

    public IssueService(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    public List<Issue> getAllIssues() {
        return issueDao.findAllByOrderById();
    }

    public Issue addIssue(Issue issue) {
        return issueDao.save(issue);
    }

    public Optional<Issue> getIssue(Long id) {
        return  issueDao.findById(id);
    }

    public Issue updateIssue(Issue issue) {
        return issueDao.save(issue);
    }
}
