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
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
        return ResponseEntity.ok(games);
    }

    @GetMapping(path = "/active")
    public ResponseEntity<GameDto> getActiveGame() {
        Game game = gameService.getActiveGame().orElse(new Game());
        return ResponseEntity.ok(new GameDto(game));
    }

    @PostMapping
    public ResponseEntity<GameDto> addNewGame(@RequestBody GameDto gameDto) {
        Game game = gameService.addGame(new Game(gameDto.getName()));
        LOGGER.info("Added game: {}", game);
        return ResponseEntity.ok(new GameDto(game));
    }

    @PutMapping
    public ResponseEntity<GameDto> updateGame(@RequestBody GameDto gameDto) {
        Optional<Game> gameOpt = gameService.getGame(gameDto.getId());
        if (gameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Game game = gameOpt.get();
        game.setName(gameDto.getName());

        Game updatedGame = gameService.updateGame(game);
        LOGGER.info("Updated updatedGame: {}", updatedGame);
        return ResponseEntity.ok(new GameDto(updatedGame));
    }
}