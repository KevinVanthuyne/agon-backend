package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all different competition formats.
 */
@Entity
@Table(name = "competitions")
public abstract class AbstractCompetition<Div extends AbstractDivision> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @OneToMany(targetEntity = AbstractDivision.class)
    protected List<Div> divisions;

    protected AbstractCompetition(int id, List<Div> divisions) {
        this.id = id;
        this.divisions = divisions;
    }

    protected AbstractCompetition() {
        this(-1, new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public void addDivision(Div division) {
        this.divisions.add(division);
    }

    public List<Div> getDivisions() {
        return divisions;
    }

    public void removeDivision(Div division) {
        this.divisions.remove(division);
    }

    public List<Div> getDivisionsByGame(Game game) {
        return divisions.stream().filter(division -> division.getGame().equals(game)).toList();
    }

    @Override
    public String toString() {
        return "AbstractCompetition{" +
                "id=" + id +
                ", divisions=" + divisions +
                '}';
    }
}
