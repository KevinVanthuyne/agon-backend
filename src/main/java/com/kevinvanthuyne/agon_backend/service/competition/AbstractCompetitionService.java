package com.kevinvanthuyne.agon_backend.service.competition;

import com.kevinvanthuyne.agon_backend.dao.competition.ICompetitionDao;
import com.kevinvanthuyne.agon_backend.model.competition.AbstractCompetition;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * Manages a single competition instance of a given {@link AbstractCompetition}. There's currently no need to manage more,
 * so this implementation is kept simple.
 */
public abstract class AbstractCompetitionService<
        Div extends AbstractDivision,
        Comp extends AbstractCompetition<Div>,
        Dao extends ICompetitionDao<Div, Comp>> {

    protected final Dao dao;
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCompetitionService.class);

    protected AbstractCompetitionService(Dao dao, Supplier<Comp> constructor) {
        this.dao = dao;

        // Create a single competition if there isn't one yet
        if (dao.findFirstByOrderById().isEmpty()) {
            LOGGER.info("No competition found. Creating one.");
            Comp competition = constructor.get();
            this.dao.save(competition);
        }
    }

    public Comp get() {
        return dao.findFirstByOrderById().orElseThrow(); // There should always be a single competition
    }

    public Comp addDivision(Div division) {
        Comp competition = this.get();
        competition.addDivision(division);
        return dao.save(competition); // Update division change in database
    }
}
