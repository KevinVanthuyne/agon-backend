package com.kevinvanthuyne.agon_backend.dao;

import com.kevinvanthuyne.agon_backend.model.Issue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueDao extends CrudRepository<Issue, Long> {
    List<Issue> findAllBy();
}
