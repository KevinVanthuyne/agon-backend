package com.kevinvanthuyne.agon_backend.api.v1.competition;

import com.kevinvanthuyne.agon_backend.model.competition.AbstractCompetition;
import com.kevinvanthuyne.agon_backend.model.competition.game.AbstractCompetitionGame;
import com.kevinvanthuyne.agon_backend.service.competition.AbstractCompetitionService;

public abstract class AbstractCompetitionController<G extends AbstractCompetitionGame,
        C extends AbstractCompetition<G>,
        S extends AbstractCompetitionService<G, C>> {
    protected S competitionService;

    protected AbstractCompetitionController(S competitionService) {
        this.competitionService = competitionService;
    }
}
