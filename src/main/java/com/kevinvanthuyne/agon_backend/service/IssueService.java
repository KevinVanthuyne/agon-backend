package com.kevinvanthuyne.agon_backend.service;

import com.kevinvanthuyne.agon_backend.dao.IssueDao;
import com.kevinvanthuyne.agon_backend.model.Issue;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    private final IssueDao issueDao;

    public IssueService(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    public Issue addIssue(Issue issue) {
        return issueDao.save(issue);
    }
}
