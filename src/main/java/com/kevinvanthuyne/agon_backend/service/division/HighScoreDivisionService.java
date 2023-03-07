package com.kevinvanthuyne.agon_backend.service.division;

import com.kevinvanthuyne.agon_backend.dao.division.HighScoreDivisionDao;
import com.kevinvanthuyne.agon_backend.model.division.HighScoreDivision;
import org.springframework.stereotype.Service;

@Service
public class HighScoreDivisionService extends AbstractDivisionService<HighScoreDivision, HighScoreDivisionDao> {
    public HighScoreDivisionService(HighScoreDivisionDao dao) {
        super(dao, HighScoreDivision::new);
    }
}
