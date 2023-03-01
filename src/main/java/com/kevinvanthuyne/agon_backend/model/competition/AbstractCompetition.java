package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.competition.division.AbstractDivision;

import java.util.List;

/**
 * Base class for all different competition formats.
 */
public abstract class AbstractCompetition<D extends AbstractDivision> {
    protected List<D> divisions;

    protected AbstractCompetition(List<D> divisions) {
        this.divisions = divisions;
    }
}
