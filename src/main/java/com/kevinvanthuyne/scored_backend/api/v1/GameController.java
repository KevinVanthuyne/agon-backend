package com.kevinvanthuyne.scored_backend.api.v1;

import com.kevinvanthuyne.scored_backend.dto.GameDto;
import com.kevinvanthuyne.scored_backend.model.Game;
import com.kevinvanthuyne.scored_backend.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/games")
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameDto> addNewGame(@RequestBody GameDto gameDto) {
        Game game = gameService.addGame(new Game(gameDto.getName()));
        LOGGER.info("Added game: {}", game);
        return ResponseEntity.ok(new GameDto(game.getId(), game.getName()));
    }
}
