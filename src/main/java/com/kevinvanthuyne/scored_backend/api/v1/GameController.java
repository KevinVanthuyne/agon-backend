package com.kevinvanthuyne.scored_backend.api.v1;

import com.kevinvanthuyne.scored_backend.dto.GameDto;
import com.kevinvanthuyne.scored_backend.model.Game;
import com.kevinvanthuyne.scored_backend.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/game")
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<GameDto> games = gameService.getAllOrdered().stream()
                .map(GameDto::new)
                .toList();
        return ResponseEntity.ok(games);
    }

    @GetMapping(path = "/passed")
    public ResponseEntity<List<GameDto>> getAllCurrentAndPassedGames() {
        List<GameDto> games = gameService.getAllCurrentAndPassedGames().stream()
                .map(GameDto::new)
                .toList();
        return ResponseEntity.ok(games);
    }

    @GetMapping(path = "/active")
    public ResponseEntity<GameDto> getActiveGame() {
        Optional<Game> gameOpt = gameService.getActiveGame();
        if (gameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GameDto(gameOpt.get()));
    }

    @PostMapping
    public ResponseEntity<GameDto> addNewGame(@RequestBody GameDto gameDto) {
        Game game = gameService.addGame(new Game(gameDto.name()));
        LOGGER.info("Added game: {}", game);
        return ResponseEntity.ok(new GameDto(game));
    }

    @PutMapping
    public ResponseEntity<GameDto> updateGame(@RequestBody GameDto gameDto) {
        Optional<Game> gameOpt = gameService.getGame(gameDto.id());
        if (gameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Game game = gameOpt.get();
        game.setName(gameDto.name());

        Game updatedGame = gameService.updateGame(game);
        LOGGER.info("Updated updatedGame: {}", updatedGame);
        return ResponseEntity.ok(new GameDto(updatedGame));
    }
}
