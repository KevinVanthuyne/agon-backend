package com.kevinvanthuyne.scored_backend.dao;

import com.kevinvanthuyne.scored_backend.model.Game;
import com.kevinvanthuyne.scored_backend.model.Score;
import com.kevinvanthuyne.scored_backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScoreDao extends CrudRepository<Score, UUID> {
    Optional<Score> findFirstByUserAndGameOrderByPointsDesc(User user, Game game);

    List<Score> findAllByGameOrderByTimestamp(Game game);

    List<Score> findAllByGameAndUserOrderByTimestamp(Game game, User user);
}
