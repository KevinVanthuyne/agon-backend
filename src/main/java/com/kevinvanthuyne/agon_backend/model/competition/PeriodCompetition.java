package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.competition.game.PeriodCompetitionGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Competition format where games are only played for a limited amount of time.
 */
public class PeriodCompetition extends AbstractCompetition<PeriodCompetitionGame> {
    public PeriodCompetition(List<PeriodCompetitionGame> games) {
        super(games);
    }

    public PeriodCompetition() {
        this(new ArrayList<>());
    }
}
