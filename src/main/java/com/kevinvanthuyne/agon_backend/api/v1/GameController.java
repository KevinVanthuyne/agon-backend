package com.kevinvanthuyne.agon_backend.api.v1;

import com.kevinvanthuyne.agon_backend.dto.GameDto;
import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.GameStyle;
import com.kevinvanthuyne.agon_backend.service.GameService;
import com.kevinvanthuyne.agon_backend.service.GameStyleService;
import com.kevinvanthuyne.agon_backend.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;
    private final GameStyleService gameStyleService;
    private final ScoreService scoreService;

    @Autowired
    public GameController(GameService gameService, GameStyleService gameStyleService, ScoreService scoreService) {
        this.gameService = gameService;
        this.gameStyleService = gameStyleService;
        this.scoreService = scoreService;
    }

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<GameDto> games = gameService.getAllOrdered().stream()
                .map(GameDto::new)
                .toList();
        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<GameDto> addNewGame(@RequestBody GameDto gameDto) {
        Game game = new Game(gameDto.name());
        game.setGameStyle(new GameStyle(game));
        Game addedGame = gameService.addGame(game);
        LOGGER.info("Added addedGame: {}", addedGame);
        return ResponseEntity.ok(new GameDto(addedGame));
    }

    @PutMapping
    public ResponseEntity<GameDto> updateGame(@RequestBody GameDto gameDto) {
        Optional<Game> gameOpt = gameService.getGame(gameDto.id());
         if (gameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Game game = gameOpt.get();
        // TODO yet another horrendous update
        if (gameDto.name() != null) {
            game.setName(gameDto.name());
        }
        if (gameDto.description() != null) {
            game.setDescription(gameDto.description());
        }
        if (gameDto.collectionHistory() != null) {
            game.setCollectionHistory(gameDto.collectionHistory());
        }
        if (gameDto.year() != null) {
            game.setYear(gameDto.year());
        }
        if (gameDto.status() != null) {
            game.setStatus(gameDto.status());
        }
        if (gameDto.category() != null) {
            game.setCategory(gameDto.category());
        }

        Game updatedGame = gameService.updateGame(game);
        LOGGER.info("Updated updatedGame: {}", updatedGame);
        return ResponseEntity.ok(new GameDto(updatedGame));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGame(@PathVariable int id) {
        Optional<Game> gameOpt = gameService.getGame(id);
        if (gameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GameDto(gameOpt.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GameDto> deleteGame(@PathVariable int id) {
        Optional<Game> gameOpt = gameService.getGame(id);
        if (gameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        gameStyleService.delete(id);
        scoreService.deleteAllScoresOfGame(gameOpt.get());
        gameService.deleteGame(id);

        return ResponseEntity.ok(new GameDto(gameOpt.get()));
    }
}
