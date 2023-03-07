package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.division.PeriodDivision;

import javax.persistence.Entity;
import java.util.List;

/**
 * Competition format where games are only played for a limited amount of time.
 */
@Entity
public class PeriodCompetition extends AbstractCompetition<PeriodDivision> {
    public PeriodCompetition(long id, List<PeriodDivision> divisions) {
        super(id, divisions);
    }

    public PeriodCompetition() {
        super();
    }
}
