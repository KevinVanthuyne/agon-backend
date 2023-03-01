package com.kevinvanthuyne.agon_backend.service.competition;

import com.kevinvanthuyne.agon_backend.model.competition.AbstractCompetition;
import com.kevinvanthuyne.agon_backend.model.competition.division.AbstractDivision;

public abstract class AbstractCompetitionService<G extends AbstractDivision, C extends AbstractCompetition<G>> {
  protected C competition;

    public AbstractCompetitionService(C competition) {
        this.competition = competition;
    }
}
