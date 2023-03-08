package com.kevinvanthuyne.agon_backend.dao;

import com.kevinvanthuyne.agon_backend.model.Score;
import com.kevinvanthuyne.agon_backend.model.User;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScoreDao extends CrudRepository<Score, UUID> {
    Optional<Score> findFirstByUserAndDivisionOrderByPointsDesc(User user, AbstractDivision division);

    List<Score> findAllByUserOrderByTimestamp(User user);

    List<Score> findAllByDivision(AbstractDivision division);
}
