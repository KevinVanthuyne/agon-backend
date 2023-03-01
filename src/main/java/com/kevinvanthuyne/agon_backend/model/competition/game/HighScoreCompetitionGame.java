package com.kevinvanthuyne.agon_backend.model.competition.game;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;

import java.util.List;

public class HighScoreCompetitionGame extends AbstractCompetitionGame{
    public HighScoreCompetitionGame(Game game, List<Score> scores) {
        super(game, scores);
    }
}
