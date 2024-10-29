package com.kevinvanthuyne.agon_backend.api.v1;

import com.kevinvanthuyne.agon_backend.dto.GameStyleDto;
import com.kevinvanthuyne.agon_backend.model.GameStyle;
import com.kevinvanthuyne.agon_backend.service.GameStyleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/game-styles")
public class GameStyleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameStyleController.class);
    private final GameStyleService gameStyleService;

    @Autowired
    public GameStyleController(GameStyleService gameStyleService) {
        this.gameStyleService = gameStyleService;
    }

    @GetMapping("/{gameId}")
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

        // TODO fix horrendous update
        GameStyle oldGameStyle = styleOpt.get();
        if (gameStyleDto.backgroundImage() != null) {
            oldGameStyle.setBackgroundImage(gameStyleDto.backgroundImage());
        }
        if (gameStyleDto.backgroundColor() != null) {
            oldGameStyle.setBackgroundColor(gameStyleDto.backgroundColor());
        }
        if (gameStyleDto.headerImage() != null) {
            oldGameStyle.setHeaderImage(gameStyleDto.headerImage());
        }
        if (gameStyleDto.borderColor() != null) {
            oldGameStyle.setBorderColor(gameStyleDto.borderColor());
        }
        if (gameStyleDto.fontColor() != null) {
            oldGameStyle.setFontColor(gameStyleDto.fontColor());
        }
        if (gameStyleDto.cabinetImage() != null) {
            oldGameStyle.setCabinetImage(gameStyleDto.cabinetImage());
        }

        GameStyle updatedGameStyle = gameStyleService.updateGameStyle(oldGameStyle);
        LOGGER.info("Updated game style: {}", updatedGameStyle);

        return ResponseEntity.ok(new GameStyleDto(updatedGameStyle));
    }
}
