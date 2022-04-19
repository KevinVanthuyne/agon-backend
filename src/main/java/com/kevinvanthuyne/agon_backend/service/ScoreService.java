package com.kevinvanthuyne.agon_backend.service;

import com.kevinvanthuyne.agon_backend.dao.ScoreDao;
import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.HighScore;
import com.kevinvanthuyne.agon_backend.model.Score;
import com.kevinvanthuyne.agon_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoreService {
    private final ScoreDao scoreDao;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public ScoreService(ScoreDao scoreDao, UserService userService, GameService gameService) {
        this.scoreDao = scoreDao;
        this.userService = userService;
        this.gameService = gameService;
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

    public Map<Integer, List<HighScore>> getAllRankings() {
        Map<Integer, List<HighScore>> rankings = new HashMap<>();

        for (Game game : gameService.getAll()) {
            rankings.put(game.getId(), getRanking(game));
        }
        return rankings;
    }

    public List<HighScore> getRanking(Game game) {
        List<HighScore> highScores = getUnrankedHighScoresPerUser(game);

        highScores.sort(Comparator.comparingLong((HighScore h) -> h.getScore().getPoints()).reversed());

        for (int i = 0; i < highScores.size(); i++) {
            highScores.get(i).setRank(i + 1);
        }

        return highScores;
    }

    public Optional<HighScore> getRankOfScore(Score score) {
        for (HighScore highScore : getRanking(score.getGame())) {
            if (highScore.getScore().getId() == score.getId()) {
                return Optional.of(highScore);
            }
        }
        return Optional.empty();
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

    public boolean deleteScore(UUID id) {
        Optional<Score> scoreOpt = scoreDao.findById(id);
        if (scoreOpt.isEmpty()) {
            return false;
        }
        scoreDao.delete(scoreOpt.get());
        return true;
    }

    /**
     * @return The HighScore of each user, without a rank on it yet.
     */
    public List<HighScore> getUnrankedHighScoresPerUser(Game game) {
        List<User> users = userService.getAllUsers();
        List<HighScore> highScores = new ArrayList<>();

        for (User user : users) {
            Optional<Score> highestScore = getHighestScore(user, game);
            highestScore.ifPresent(score -> highScores.add(new HighScore(score)));
        }
        return highScores;
    }

}
