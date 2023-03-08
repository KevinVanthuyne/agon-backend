package com.kevinvanthuyne.agon_backend.dao.division;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IDivisionDao<Div extends AbstractDivision> extends CrudRepository<Div, Integer> {
    List<Div> findAllByGame(Game game);
}
