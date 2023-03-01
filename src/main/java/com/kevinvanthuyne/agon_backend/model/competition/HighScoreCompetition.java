package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.competition.division.HighScoreDivision;

import java.util.ArrayList;
import java.util.List;

/**
 * Competition format that runs endlessly for multiple games at once.
 */
public class HighScoreCompetition extends AbstractCompetition<HighScoreDivision> {
    public HighScoreCompetition(List<HighScoreDivision> divisions) {
        super(divisions);
    }

    public HighScoreCompetition() {
        this(new ArrayList<>());
    }
}
