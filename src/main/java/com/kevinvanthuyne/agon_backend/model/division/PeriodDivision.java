package com.kevinvanthuyne.agon_backend.model.division;

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

    public PeriodDivision(int id, Game game, List<Score> scores, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(id, game, scores);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public PeriodDivision(Game game) {
        this(-1, game, List.of(), LocalDateTime.MIN, LocalDateTime.MIN);
    }

    public PeriodDivision() {
        this(null);
    }
}
