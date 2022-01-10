package com.kevinvanthuyne.scored_backend.service;

import com.kevinvanthuyne.scored_backend.dao.ScoreDao;
import com.kevinvanthuyne.scored_backend.model.Score;
import com.kevinvanthuyne.scored_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreService {
    private final ScoreDao scoreDao;

    @Autowired
    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public Score addScore(Score score) {
        return scoreDao.save(score);
    }

    public Optional<Score> getHighestScore(User user) {
        return scoreDao.findFirstByUserOrderByScoreDesc(user);
    }
}
