package com.kevinvanthuyne.agon_backend.model.competition.division;

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

    protected AbstractDivision(Game game, List<Score> scores) {
        this.game = game;
        this.scores = scores;
    }

    public AbstractDivision() {}
}
