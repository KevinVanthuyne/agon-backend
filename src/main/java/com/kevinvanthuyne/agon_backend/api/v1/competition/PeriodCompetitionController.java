package com.kevinvanthuyne.agon_backend.api.v1.competition;

import com.kevinvanthuyne.agon_backend.dao.competition.PeriodCompetitionDao;
import com.kevinvanthuyne.agon_backend.dao.division.PeriodDivisionDao;
import com.kevinvanthuyne.agon_backend.model.competition.PeriodCompetition;
import com.kevinvanthuyne.agon_backend.model.division.PeriodDivision;
import com.kevinvanthuyne.agon_backend.service.GameService;
import com.kevinvanthuyne.agon_backend.service.competition.PeriodCompetitionService;
import com.kevinvanthuyne.agon_backend.service.division.PeriodDivisionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AbstractCompetitionController.BASE_URL + "/period")
public class PeriodCompetitionController extends AbstractCompetitionController<
        PeriodDivision,
        PeriodDivisionDao,
        PeriodDivisionService,
        PeriodCompetition,
        PeriodCompetitionDao,
        PeriodCompetitionService> {

    public PeriodCompetitionController(PeriodCompetitionService competitionService,
                                       PeriodDivisionService divisionService,
                                       GameService gameService) {
        super(competitionService, divisionService, gameService);
    }
}
