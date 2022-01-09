package com.kevinvanthuyne.scored_backend.dao;

import com.kevinvanthuyne.scored_backend.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameDao extends CrudRepository<Game, Integer> {
}
