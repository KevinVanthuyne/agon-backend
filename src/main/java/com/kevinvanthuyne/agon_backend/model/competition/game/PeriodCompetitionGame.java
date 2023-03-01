package com.kevinvanthuyne.agon_backend.model.competition.game;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;

import java.time.LocalDateTime;
import java.util.List;

public class PeriodCompetitionGame extends AbstractCompetitionGame {
    private LocalDateTime start;
    private LocalDateTime end;

    public PeriodCompetitionGame(Game game, List<Score> scores) {
        super(game, scores);
    }
}
