package com.kevinvanthuyne.scored_backend.dao;

import com.kevinvanthuyne.scored_backend.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameDao extends CrudRepository<Game, Integer> {
    List<Game> findAll();

    Game findFirstOrderByIdDesc();
}
