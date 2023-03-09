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

    /**
     * @return A Map of all active {@link AbstractDivision} ids and their {@link Score}s, sorted by highest score first.
     */
    public Map<Integer, List<Score>> getSortedScoresOfAllActiveDivisions() {
        HashMap<Integer, List<Score>> divisionsAndScores = new HashMap<>();

        for (AbstractDivision division : divisionService.getAllActive()) {
            List<Score> scores = scoreDao.findAllByDivision(division);
            List<Score> sortedScores = sortScores(scores);
            divisionsAndScores.put(division.getId(), sortedScores);
        }
        return divisionsAndScores;
    }

    /**
     * @return The highest {@link Score} of the given {@link User} in the given {@link AbstractDivision}.
     */
    public Optional<Score> getHighestScore(User user, AbstractDivision division) {
        return scoreDao.findFirstByUserAndDivisionOrderByPointsDesc(user, division);
    }

    /**
     * @return List of given {@link Score}s, sorted by most points first.
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
     * @return The amount of {@link Score} entries in the given {@link AbstractDivision}.
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
