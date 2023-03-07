package com.kevinvanthuyne.agon_backend.model.competition;

import com.kevinvanthuyne.agon_backend.model.division.HighScoreDivision;

import javax.persistence.Entity;
import java.util.List;

/**
 * Competition format that runs endlessly for multiple games at once.
 */
@Entity
public class HighScoreCompetition extends AbstractCompetition<HighScoreDivision> {
    public HighScoreCompetition(long id, List<HighScoreDivision> divisions) {
        super(id, divisions);
    }

    public HighScoreCompetition() {
        super();
    }
}
