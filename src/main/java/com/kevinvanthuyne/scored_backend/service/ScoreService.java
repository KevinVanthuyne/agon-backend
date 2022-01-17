package com.kevinvanthuyne.scored_backend.service;

import com.kevinvanthuyne.scored_backend.dao.ScoreDao;
import com.kevinvanthuyne.scored_backend.model.Game;
import com.kevinvanthuyne.scored_backend.model.HighScore;
import com.kevinvanthuyne.scored_backend.model.Score;
import com.kevinvanthuyne.scored_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoreService {
    private final ScoreDao scoreDao;
    private final UserService userService;

    @Autowired
    public ScoreService(ScoreDao scoreDao, UserService userService) {
        this.scoreDao = scoreDao;
        this.userService = userService;
    }

    public Score addScore(Score score) {
        return scoreDao.save(score);
    }

    public Optional<Score> getScore(UUID id) {
        return scoreDao.findById(id);
    }

    public Optional<Score> getHighestScore(User user, Game game) {
        return scoreDao.findFirstByUserAndGameOrderByPointsDesc(user, game);
    }

    public List<HighScore> getRanking(Game game) {
        List<User> users = userService.getAllUsers();
        List<HighScore> highScores = new ArrayList<>();

        for (User user : users) {
            Optional<Score> highestScore = getHighestScore(user, game);
            highestScore.ifPresent(score -> highScores.add(new HighScore(score)));
        }

        highScores.sort(Comparator.comparingLong((HighScore h) -> h.getScore().getPoints()).reversed());

        for (int i = 0; i < highScores.size(); i++) {
            highScores.get(i).setRank(i + 1);
        }

        return highScores;
    }

    public List<Score> getScores(Game game) {
        return scoreDao.findAllByGameOrderByTimestamp(game);
    }

    public List<Score> getScores(User user) {
        return scoreDao.findAllByUserOrderByTimestamp(user);
    }

    public List<Score> getScores(Game game, User user) {
        return scoreDao.findAllByGameAndUserOrderByTimestamp(game, user);
    }
}
