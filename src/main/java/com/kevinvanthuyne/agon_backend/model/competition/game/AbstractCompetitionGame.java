package com.kevinvanthuyne.agon_backend.model.competition.game;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;

import java.util.List;

/**
 * Base class for all entries of a competition. Contains a game and its scores.
 */
public abstract class AbstractCompetitionGame {
    protected Game game;
    protected List<Score> scores;

    protected AbstractCompetitionGame(Game game, List<Score> scores) {
        this.game = game;
        this.scores = scores;
    }
}
