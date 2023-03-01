package com.kevinvanthuyne.agon_backend.model.competition.division;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Division with a start and end date.
 */
@Entity
public class PeriodDivision extends AbstractDivision {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public PeriodDivision(Game game, List<Score> scores) {
        super(game, scores);
    }

    public PeriodDivision() {
    }
}
