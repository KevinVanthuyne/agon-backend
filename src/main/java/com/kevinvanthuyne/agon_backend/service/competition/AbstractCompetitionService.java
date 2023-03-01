package com.kevinvanthuyne.agon_backend.service.competition;

import com.kevinvanthuyne.agon_backend.model.competition.AbstractCompetition;
import com.kevinvanthuyne.agon_backend.model.competition.game.AbstractCompetitionGame;

public abstract class AbstractCompetitionService<G extends AbstractCompetitionGame, C extends AbstractCompetition<G>> {
  protected C competition;

    public AbstractCompetitionService(C competition) {
        this.competition = competition;
    }
}
