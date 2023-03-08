package com.kevinvanthuyne.agon_backend.service.division;

import com.kevinvanthuyne.agon_backend.dao.division.IDivisionDao;
import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;

import java.util.List;
import java.util.Optional;
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

    /**
     * @return Optional containing the division for the given id if it exists, or an empty Optional if it does not exist.
     */
    public Optional<Div> get(int divisionId) {
        return dao.findById(divisionId);
    }

    /**
     * @return List of divisions linked to the given game.
     */
    public List<Div> getAll(Game game) {
        return dao.findAllByGame(game);
    }

    /**
     * Deletes the given division.
     */
    public void delete(Div division) {
        dao.delete(division);
    }
}
