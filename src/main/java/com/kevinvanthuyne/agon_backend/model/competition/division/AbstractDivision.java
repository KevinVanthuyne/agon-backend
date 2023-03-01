package com.kevinvanthuyne.agon_backend.model.competition.division;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;

import java.util.List;

/**
 * Base class for all divisions of a competition. Contains an instance of a game and its scores.
 */
public abstract class AbstractDivision {
    protected Game game;
    protected List<Score> scores;

    protected AbstractDivision(Game game, List<Score> scores) {
        this.game = game;
        this.scores = scores;
    }
}
