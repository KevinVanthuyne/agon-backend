package com.kevinvanthuyne.agon_backend.model.division;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Score;

import javax.persistence.Entity;
import java.util.List;

/**
 * Regular division that can be played eternally.
 */
@Entity
public class HighScoreDivision extends AbstractDivision {
    public HighScoreDivision(int id, Game game, List<Score> scores) {
        super(id, game, scores);
    }

    public HighScoreDivision(Game game) {
        this(-1, game, List.of());
    }

    public HighScoreDivision() {
        this(null);
    }
}
