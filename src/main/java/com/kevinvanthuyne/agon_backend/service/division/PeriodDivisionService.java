package com.kevinvanthuyne.agon_backend.service.division;

import com.kevinvanthuyne.agon_backend.dao.division.PeriodDivisionDao;
import com.kevinvanthuyne.agon_backend.model.division.PeriodDivision;
import org.springframework.stereotype.Service;

@Service
public class PeriodDivisionService extends AbstractDivisionService<PeriodDivision, PeriodDivisionDao> {
    public PeriodDivisionService(PeriodDivisionDao dao) {
        super(dao, PeriodDivision::new);
    }
}
