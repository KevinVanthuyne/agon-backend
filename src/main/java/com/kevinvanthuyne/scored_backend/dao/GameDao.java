package com.kevinvanthuyne.scored_backend.dao;

import com.kevinvanthuyne.scored_backend.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GameDao extends CrudRepository<Game, Integer> {
    List<Game> findAllBy();

    List<Game> findAllByOrderByIdAsc();

    List<Game> findAllByStartDateBefore(LocalDate localDate);

    Game findFirstByOrderByIdAsc();

    Optional<Game> findFirstByStartDateBetween(LocalDate start, LocalDate end);
}
