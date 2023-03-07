package com.kevinvanthuyne.agon_backend.service.division;

import com.kevinvanthuyne.agon_backend.dao.division.IDivisionDao;
import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;

import java.util.function.Function;

public abstract class AbstractDivisionService<Div extends AbstractDivision, Dao extends IDivisionDao<Div>> {
    private final Dao dao;
    private final Function<Game, Div> constructor;

    public AbstractDivisionService(Dao dao, Function<Game, Div> constructor) {
        this.dao = dao;
        this.constructor = constructor;
    }

    /**
     * Creates a division and saves it to the database;
     */
    public Div create(Game game) {
        Div division = constructor.apply(game);
        return dao.save(division);
    }
}
