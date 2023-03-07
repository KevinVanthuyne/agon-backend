package com.kevinvanthuyne.agon_backend.service.competition;

import com.kevinvanthuyne.agon_backend.dao.competition.PeriodCompetitionDao;
import com.kevinvanthuyne.agon_backend.model.competition.PeriodCompetition;
import com.kevinvanthuyne.agon_backend.model.division.PeriodDivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodCompetitionService extends AbstractCompetitionService<
        PeriodDivision,
        PeriodCompetition,
        PeriodCompetitionDao> {

    @Autowired
    public PeriodCompetitionService(PeriodCompetitionDao dao) {
        super(dao, PeriodCompetition::new);
    }
}
