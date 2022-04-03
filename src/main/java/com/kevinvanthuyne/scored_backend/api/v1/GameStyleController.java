package com.kevinvanthuyne.scored_backend.api.v1;

import com.kevinvanthuyne.scored_backend.dto.GameStyleDto;
import com.kevinvanthuyne.scored_backend.model.GameStyle;
import com.kevinvanthuyne.scored_backend.service.GameStyleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/game-style")
public class GameStyleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameStyleController.class);
    private final GameStyleService gameStyleService;

    @Autowired
    public GameStyleController(GameStyleService gameStyleService) {
        this.gameStyleService = gameStyleService;
    }

    @GetMapping(path = "/{gameId}")
    public ResponseEntity<GameStyleDto> getGameStyle(@PathVariable int gameId) {
        Optional<GameStyle> gameStyleOpt = gameStyleService.getStyle(gameId);
        if (gameStyleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GameStyleDto(gameStyleOpt.get()));
    }

    @PutMapping
    public ResponseEntity<GameStyleDto> updateGameStyle(@RequestBody GameStyleDto gameStyleDto) {
        Optional<GameStyle> styleOpt = gameStyleService.getStyle(gameStyleDto.gameId());
        if (styleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        GameStyle updatedGameStyle = gameStyleService.updateGameStyle(styleOpt.get());
        LOGGER.info("Updated game style: {}", updatedGameStyle);

        return ResponseEntity.ok(new GameStyleDto(updatedGameStyle));
    }
}
