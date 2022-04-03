package com.kevinvanthuyne.scored_backend.dao;

import com.kevinvanthuyne.scored_backend.model.GameStyle;
import org.springframework.data.repository.CrudRepository;

public interface GameStyleDao extends CrudRepository<GameStyle, Integer> {
}
