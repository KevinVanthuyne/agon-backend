package com.kevinvanthuyne.agon_backend.dao;

import com.kevinvanthuyne.agon_backend.model.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueDao extends CrudRepository<Issue, Long> {
}
