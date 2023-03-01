package com.kevinvanthuyne.agon_backend.api.v1.competition;

import com.kevinvanthuyne.agon_backend.model.competition.HighScoreCompetition;
import com.kevinvanthuyne.agon_backend.model.competition.division.HighScoreDivision;
import com.kevinvanthuyne.agon_backend.service.competition.HighScoreCompetitionService;

public class HighScoreCompetitionController extends AbstractCompetitionController<HighScoreDivision, HighScoreCompetition, HighScoreCompetitionService> {
    public HighScoreCompetitionController(HighScoreCompetitionService competitionService) {
        super(competitionService);
    }
}
