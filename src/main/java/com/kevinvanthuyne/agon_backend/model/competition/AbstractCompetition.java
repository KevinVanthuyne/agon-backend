package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.competition.game.AbstractCompetitionGame;

import java.util.List;

/**
 * Base class for all different competition formats.
 */
public abstract class AbstractCompetition<G extends AbstractCompetitionGame> {
    protected List<G> games;

    protected AbstractCompetition(List<G> games) {
        this.games = games;
    }
}
