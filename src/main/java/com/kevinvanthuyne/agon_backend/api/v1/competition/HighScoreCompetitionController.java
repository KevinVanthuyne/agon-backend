package com.kevinvanthuyne.agon_backend.api.v1.competition;

import com.kevinvanthuyne.agon_backend.dao.competition.HighScoreCompetitionDao;
import com.kevinvanthuyne.agon_backend.dao.division.HighScoreDivisionDao;
import com.kevinvanthuyne.agon_backend.model.competition.HighScoreCompetition;
import com.kevinvanthuyne.agon_backend.model.division.HighScoreDivision;
import com.kevinvanthuyne.agon_backend.service.GameService;
import com.kevinvanthuyne.agon_backend.service.competition.HighScoreCompetitionService;
import com.kevinvanthuyne.agon_backend.service.division.HighScoreDivisionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AbstractCompetitionController.BASE_URL + "/high-score")
public class HighScoreCompetitionController extends AbstractCompetitionController<
        HighScoreDivision,
        HighScoreDivisionDao,
        HighScoreDivisionService,
        HighScoreCompetition,
        HighScoreCompetitionDao,
        HighScoreCompetitionService> {

    public HighScoreCompetitionController(HighScoreCompetitionService competitionService,
                                          HighScoreDivisionService divisionService,
                                          GameService gameService) {
        super(competitionService, divisionService, gameService);
    }
}
