package com.kevinvanthuyne.scored_backend.dao;

import com.kevinvanthuyne.scored_backend.model.Game;
import com.kevinvanthuyne.scored_backend.model.GameStyle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameStyleDao extends CrudRepository<GameStyle, Game> {
    List<GameStyle> findAllByOrderByGameIdAsc();
}
