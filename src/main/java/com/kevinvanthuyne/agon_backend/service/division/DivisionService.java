package com.kevinvanthuyne.agon_backend.service.division;

import com.kevinvanthuyne.agon_backend.dao.division.IDivisionDao;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;
import org.springframework.stereotype.Service;

/**
 * Generic division service used to perform operations that are valid for all {@link AbstractDivision}s like retrieving
 * all divisions, independent of what subclass.
 */
@Service
public class DivisionService extends AbstractDivisionService<AbstractDivision, IDivisionDao<AbstractDivision>> {
    public DivisionService(IDivisionDao<AbstractDivision> dao) {
        super(dao, (game) -> {
            throw new RuntimeException("Should never be called");
        });
    }
}
