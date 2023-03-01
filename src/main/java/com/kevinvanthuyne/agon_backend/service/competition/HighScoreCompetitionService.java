package com.kevinvanthuyne.agon_backend.service.competition;

import com.kevinvanthuyne.agon_backend.model.competition.HighScoreCompetition;
import com.kevinvanthuyne.agon_backend.model.competition.division.HighScoreDivision;
import org.springframework.stereotype.Service;

@Service
public class HighScoreCompetitionService extends AbstractCompetitionService<HighScoreDivision, HighScoreCompetition> {
    public HighScoreCompetitionService() {
        super(new HighScoreCompetition());
    }
}
