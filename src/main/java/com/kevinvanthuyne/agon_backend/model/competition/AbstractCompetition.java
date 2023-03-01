package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.competition.division.AbstractDivision;

import javax.persistence.*;
import java.util.List;

/**
 * Base class for all different competition formats.
 */
@Entity
@Table(name = "competitions")
public abstract class AbstractCompetition<D extends AbstractDivision> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @OneToMany(targetEntity = AbstractDivision.class)
    protected List<D> divisions;

    protected AbstractCompetition(List<D> divisions) {
        this.divisions = divisions;
    }

    public AbstractCompetition() {}
}
