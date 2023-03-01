package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.competition.division.PeriodDivision;

import java.util.ArrayList;
import java.util.List;

/**
 * Competition format where games are only played for a limited amount of time.
 */
public class PeriodCompetition extends AbstractCompetition<PeriodDivision> {
    public PeriodCompetition(List<PeriodDivision> divisions) {
        super(divisions);
    }

    public PeriodCompetition() {
        this(new ArrayList<>());
    }
}
