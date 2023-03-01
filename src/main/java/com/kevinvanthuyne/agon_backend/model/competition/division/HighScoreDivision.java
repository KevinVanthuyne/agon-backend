package com.kevinvanthuyne.agon_backend.model.competition.division;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;

import java.util.List;

/**
 * Regular division that can be played eternally.
 */
public class HighScoreDivision extends AbstractDivision {
    public HighScoreDivision(Game game, List<Score> scores) {
        super(game, scores);
    }
}
