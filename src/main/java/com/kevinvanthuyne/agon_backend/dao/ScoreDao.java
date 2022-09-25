package com.kevinvanthuyne.agon_backend.dao;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;
import com.kevinvanthuyne.agon_backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScoreDao extends CrudRepository<Score, UUID> {
    Optional<Score> findFirstByUserAndGameOrderByPointsDesc(User user, Game game);

    List<Score> findAllByGameOrderByTimestamp(Game game);

    List<Score> findAllByUserOrderByTimestamp(User user);

    List<Score> findAllByGameAndUserOrderByTimestamp(Game game, User user);

    List<Score> findAllByGame(Game game);
}
