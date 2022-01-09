package com.kevinvanthuyne.scored_backend.api.v1;

import com.kevinvanthuyne.scored_backend.dto.ScoreDto;
import com.kevinvanthuyne.scored_backend.model.Game;
import com.kevinvanthuyne.scored_backend.model.Score;
import com.kevinvanthuyne.scored_backend.model.User;
import com.kevinvanthuyne.scored_backend.service.GameService;
import com.kevinvanthuyne.scored_backend.service.ScoreService;
import com.kevinvanthuyne.scored_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/scores")
public class ScoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreController.class);
    private final ScoreService scoreService;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public ScoreController(ScoreService scoreService, UserService userService, GameService gameService) {
        this.scoreService = scoreService;
        this.userService = userService;
        this.gameService = gameService;
    }

    @GetMapping
    public List<ScoreDto> getAllScores() {
        return new ArrayList<>();
    }

    @PostMapping
    public ResponseEntity<Score> addNewScore(@RequestBody ScoreDto scoreDto) {
        LOGGER.info("New score received: {}", scoreDto);

        Optional<Game> gameOpt = gameService.getGame(scoreDto.getGameId());
        if (gameOpt.isEmpty()) {
            LOGGER.info("Could not find game id {}", scoreDto.getGameId());
            return ResponseEntity.badRequest().build();
        }

        Optional<User> userOpt = userService.getUser(scoreDto.getUserId());
        if (userOpt.isEmpty()) {
            User user = userService.addUser(new User(scoreDto.getUserId(), scoreDto.getUsername()));
            LOGGER.info("New userOpt added: {}", user);
            userOpt = Optional.of(user);
        }

        Score score = scoreService.addScore(new Score(scoreDto.getScore(), scoreDto.getScoreImageUrl(), userOpt.get(), gameOpt.get()));
        LOGGER.info("Score added: {}", score);

        return ResponseEntity.of(Optional.of(score));
    }
}
