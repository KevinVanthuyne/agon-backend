package com.kevinvanthuyne.agon_backend.dao;

import com.kevinvanthuyne.agon_backend.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameDao extends CrudRepository<Game, Integer> {
    List<Game> findAllBy();

    List<Game> findAllByOrderByIdAsc();
}
