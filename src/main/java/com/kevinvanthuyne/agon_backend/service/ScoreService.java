package com.kevinvanthuyne.agon_backend.service;

import com.kevinvanthuyne.agon_backend.dao.ScoreDao;
import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;
import com.kevinvanthuyne.agon_backend.model.User;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;
import com.kevinvanthuyne.agon_backend.service.division.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoreService {
    private final ScoreDao scoreDao;
    private final DivisionService divisionService;

    @Autowired
    public ScoreService(ScoreDao scoreDao, DivisionService divisionService) {
        this.scoreDao = scoreDao;
        this.divisionService = divisionService;
    }

    public Score addScore(Score score) {
        return scoreDao.save(score);
    }

    public Optional<Score> getScore(UUID id) {
        return scoreDao.findById(id);
    }

    public Optional<Score> getHighestScore(User user, AbstractDivision division) {
        return scoreDao.findFirstByUserAndDivisionOrderByPointsDesc(user, division);
    }

    /**
     * @return List of scores for the given division, sorted by most points first.
     */
    public List<Score> sortScores(List<Score> scores) {
        scores.sort(Comparator.comparingLong(Score::getPoints).reversed());
        return scores;
    }

    /**
     * @return The rank of the score compared to all other scores in that division.
     */
    public int getRankOfScore(Score score) {
        List<Score> allScores = new ArrayList<>(List.of(score));
        allScores.addAll(score.getDivision().getScores());
        List<Score> sortedScores = sortScores(allScores);
        return sortedScores.indexOf(score) + 1;
    }

    /**
     * @return The amount of score entries in the given division.
     */
    public int getAmountOfScores(AbstractDivision division) {
        return division.getScores().size();
    }

    public List<Score> getScores(User user) {
        return scoreDao.findAllByUserOrderByTimestamp(user);
    }

    public void deleteScore(UUID id) {
        Optional<Score> scoreOpt = scoreDao.findById(id);
        if (scoreOpt.isEmpty()) return;
        scoreDao.delete(scoreOpt.get());
    }

    public void deleteAllScoresOfGame(Game game) {
        for (AbstractDivision division : divisionService.getAll(game)) {
            deleteAllScoresOfDivision(division);
        }
    }

    public void deleteAllScoresOfDivision(AbstractDivision division) {
        for (Score score : scoreDao.findAllByDivision(division)) {
            deleteScore(score.getId());
        }
    }
}
