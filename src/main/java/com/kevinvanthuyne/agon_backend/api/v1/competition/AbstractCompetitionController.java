package com.kevinvanthuyne.agon_backend.api.v1.competition;

import com.kevinvanthuyne.agon_backend.dao.competition.ICompetitionDao;
import com.kevinvanthuyne.agon_backend.dao.division.IDivisionDao;
import com.kevinvanthuyne.agon_backend.dto.division.DivisionDto;
import com.kevinvanthuyne.agon_backend.dto.division.NewDivisionDto;
import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.competition.AbstractCompetition;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;
import com.kevinvanthuyne.agon_backend.service.GameService;
import com.kevinvanthuyne.agon_backend.service.competition.AbstractCompetitionService;
import com.kevinvanthuyne.agon_backend.service.division.AbstractDivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCompetitionController<
        Div extends AbstractDivision,
        DivDao extends IDivisionDao<Div>,
        DivServ extends AbstractDivisionService<Div, DivDao>,
        Comp extends AbstractCompetition<Div>,
        CompDao extends ICompetitionDao<Div, Comp>,
        CompServ extends AbstractCompetitionService<Div, Comp, CompDao>> {

    protected static final String BASE_URL = "/api/v1/competitions";
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCompetitionController.class);

    protected final CompServ competitionService;
    protected final DivServ divisionService;
    private final GameService gameService;

    protected AbstractCompetitionController(CompServ competitionService,
                                            DivServ divisionService,
                                            GameService gameService) {
        this.competitionService = competitionService;
        this.divisionService = divisionService;
        this.gameService = gameService;
    }

    /**
     * @return The list of {@link Div} contained in the competition.
     */
    @GetMapping("/divisions")
    public ResponseEntity<List<DivisionDto>> getDivisions() {
        List<DivisionDto> divisionDtos = competitionService.get().getDivisions().stream()
                .map(DivisionDto::new)
                .toList();
        return ResponseEntity.ok(divisionDtos);
    }

    /**
     * Add a {@link Div} to the competition.
     *
     * @param divisionDto Division DTO to add to the competition.
     * @return The {@link Div} that was added to the competition.
     */
    @PostMapping("/divisions")
    public ResponseEntity<DivisionDto> addDivision(@RequestBody NewDivisionDto divisionDto) {
        Optional<Game> gameOpt = gameService.getGame(divisionDto.gameId());
        if (gameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Div division = divisionService.create(gameOpt.get());
        Comp competition = competitionService.addDivision(division);
        LOGGER.info("Added division {} to competition {}.", division.getId(), competition.getId());

        return ResponseEntity.ok(new DivisionDto(division));
    }

    /**
     * Deletes the {@link Div} with the given id;
     *
     * @return Nothing if delete was successful, an error message if the division could not be found.
     */
    @DeleteMapping("/divisions/{divisionId}")
    public ResponseEntity<?> deleteDivision(@PathVariable int divisionId) {
        Optional<Div> divisionOpt = divisionService.get(divisionId);
        if (divisionOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Division could not be found.");
        }
        divisionService.delete(divisionOpt.get());
        return ResponseEntity.ok().build();
    }
}
