package com.kevinvanthuyne.agon_backend.service.competition;

import com.kevinvanthuyne.agon_backend.model.competition.PeriodCompetition;
import com.kevinvanthuyne.agon_backend.model.competition.division.PeriodDivision;
import org.springframework.stereotype.Service;

@Service
public class PeriodCompetitionService extends AbstractCompetitionService<PeriodDivision, PeriodCompetition> {
    public PeriodCompetitionService() {
        super(new PeriodCompetition());
    }
}
