package com.kevinvanthuyne.agon_backend.dao;

import com.kevinvanthuyne.agon_backend.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface GameDao extends CrudRepository<Game, Integer> {
    List<Game> findAllBy();

    List<Game> findAllByOrderByIdAsc();

    List<Game> findAllByOrderByStartDateAsc();

    List<Game> findAllByStartDateLessThanEqual(LocalDate localDate);

    Game findFirstByOrderByIdAsc();
}
