package com.kevinvanthuyne.agon_backend.dao;

import com.kevinvanthuyne.agon_backend.model.GameStyle;
import org.springframework.data.repository.CrudRepository;

public interface GameStyleDao extends CrudRepository<GameStyle, Integer> {
}
