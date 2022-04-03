package com.kevinvanthuyne.scored_backend.api.v1;

import com.kevinvanthuyne.scored_backend.dto.GameStyleDto;
import com.kevinvanthuyne.scored_backend.model.Game;
import com.kevinvanthuyne.scored_backend.model.GameStyle;
import com.kevinvanthuyne.scored_backend.service.GameService;
import com.kevinvanthuyne.scored_backend.service.GameStyleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/game-style")
public class GameStyleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameStyleController.class);
    private final GameStyleService gameStyleService;
    private final GameService gameService;

    @Autowired
    public GameStyleController(GameStyleService gameStyleService, GameService gameService) {
        this.gameStyleService = gameStyleService;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameStyleDto>> getAllGameStyles() {
        List<GameStyleDto> gameStyles = gameStyleService.getAllOrdered().stream()
                .map(GameStyleDto::new)
                .toList();
        return ResponseEntity.ok(gameStyles);
    }

    @PutMapping
    public ResponseEntity<GameStyleDto> updateGameStyle(@RequestBody GameStyleDto gameStyleDto) {
        Optional<Game> gameOpt = gameService.getGame(gameStyleDto.gameId());
        if (gameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<GameStyle> styleOpt = gameStyleService.getStyle(gameOpt.get());
        if (styleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        GameStyle updatedGameStyle = gameStyleService.updateGameStyle(styleOpt.get());
        LOGGER.info("Updated game style: {}", updatedGameStyle);

        return ResponseEntity.ok(new GameStyleDto(updatedGameStyle));
    }
}
