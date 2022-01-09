package com.kevinvanthuyne.scored_backend.dao;

import com.kevinvanthuyne.scored_backend.model.Score;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ScoreDao extends CrudRepository<Score, UUID> {
}
