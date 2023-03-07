package com.kevinvanthuyne.agon_backend.model.division;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;

import javax.persistence.*;
import java.util.List;

/**
 * Base class for all divisions of a competition. Contains an instance of a game and its scores to be used in a {@link com.kevinvanthuyne.agon_backend.model.competition.AbstractCompetition}.
 */
@Entity
@Table(name = "divisions")
public abstract class AbstractDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    protected Game game;

    @OneToMany
    protected List<Score> scores;


    protected AbstractDivision(int id, Game game, List<Score> scores) {
        this.id = id;
        this.game = game;
        this.scores = scores;
    }

    protected AbstractDivision() {
        this(-1, null, List.of());
    }

    public int getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }
}
