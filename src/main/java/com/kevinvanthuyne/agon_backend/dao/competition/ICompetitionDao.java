package com.kevinvanthuyne.agon_backend.dao.competition;

import com.kevinvanthuyne.agon_backend.model.competition.AbstractCompetition;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICompetitionDao<
        Div extends AbstractDivision,
        Comp extends AbstractCompetition<Div>> extends CrudRepository<Comp, Long> {

    Optional<Comp> findFirstByOrderById();

    Optional<Comp> findFirstByDivisionsOrderById(Div division);
}
