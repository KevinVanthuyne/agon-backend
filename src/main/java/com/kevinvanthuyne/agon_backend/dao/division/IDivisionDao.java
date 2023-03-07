package com.kevinvanthuyne.agon_backend.dao.division;

import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;
import org.springframework.data.repository.CrudRepository;

public interface IDivisionDao<Div extends AbstractDivision> extends CrudRepository<Div, Integer> {
}
