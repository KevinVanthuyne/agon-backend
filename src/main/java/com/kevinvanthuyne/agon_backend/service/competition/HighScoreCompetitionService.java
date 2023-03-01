package com.kevinvanthuyne.agon_backend.service.competition;

import com.kevinvanthuyne.agon_backend.model.competition.HighScoreCompetition;
import com.kevinvanthuyne.agon_backend.model.competition.game.HighScoreCompetitionGame;
import org.springframework.stereotype.Service;

@Service
public class HighScoreCompetitionService extends AbstractCompetitionService<HighScoreCompetitionGame, HighScoreCompetition> {
    public HighScoreCompetitionService() {
        super(new HighScoreCompetition());
    }
}
