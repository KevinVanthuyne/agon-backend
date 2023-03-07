package com.kevinvanthuyne.agon_backend.service.competition;

import com.kevinvanthuyne.agon_backend.dao.competition.HighScoreCompetitionDao;
import com.kevinvanthuyne.agon_backend.model.competition.HighScoreCompetition;
import com.kevinvanthuyne.agon_backend.model.division.HighScoreDivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HighScoreCompetitionService extends AbstractCompetitionService<
        HighScoreDivision,
        HighScoreCompetition,
        HighScoreCompetitionDao> {

    @Autowired
    public HighScoreCompetitionService(HighScoreCompetitionDao dao) {
        super(dao, HighScoreCompetition::new);
    }
}
