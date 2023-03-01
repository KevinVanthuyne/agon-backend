package com.kevinvanthuyne.agon_backend.model.competition.division;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Division with a start and end date.
 */
public class PeriodDivision extends AbstractDivision {
    private LocalDateTime start;
    private LocalDateTime end;

    public PeriodDivision(Game game, List<Score> scores) {
        super(game, scores);
    }
}
