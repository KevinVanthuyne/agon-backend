package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.competition.game.HighScoreCompetitionGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Competition format that runs endlessly for multiple games at once.
 */
public class HighScoreCompetition extends AbstractCompetition<HighScoreCompetitionGame> {
    public HighScoreCompetition(List<HighScoreCompetitionGame> games) {
        super(games);
    }

    public HighScoreCompetition() {
        this(new ArrayList<>());
    }
}
