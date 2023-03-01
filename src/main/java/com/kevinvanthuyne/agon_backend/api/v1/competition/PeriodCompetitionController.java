package com.kevinvanthuyne.agon_backend.api.v1.competition;

import com.kevinvanthuyne.agon_backend.model.competition.PeriodCompetition;
import com.kevinvanthuyne.agon_backend.model.competition.division.PeriodDivision;
import com.kevinvanthuyne.agon_backend.service.competition.PeriodCompetitionService;

public class PeriodCompetitionController extends AbstractCompetitionController<PeriodDivision, PeriodCompetition, PeriodCompetitionService> {
    public PeriodCompetitionController(PeriodCompetitionService competitionService) {
        super(competitionService);
    }
}
