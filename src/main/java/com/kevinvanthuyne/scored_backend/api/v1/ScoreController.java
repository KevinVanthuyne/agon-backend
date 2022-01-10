package com.kevinvanthuyne.scored_backend.api.v1;

import com.kevinvanthuyne.scored_backend.dto.HighScoreDto;
import com.kevinvanthuyne.scored_backend.dto.ScoreDto;
import com.kevinvanthuyne.scored_backend.dto.ScorePostedDto;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/{gameId}/ranking")
    public ResponseEntity<List<HighScoreDto>> getAllScores(@PathVariable int gameId) {
        Optional<Game> gameOpt = gameService.getGame(gameId);
        if (gameOpt.isEmpty()) {
            LOGGER.info("Could not find game id {}", gameId);
            return ResponseEntity.badRequest().build();
        }

        List<HighScoreDto> highScores = scoreService.getRanking(gameOpt.get()).stream()
                .map(HighScoreDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.of(Optional.of(highScores));
    }

    @PostMapping
    public ResponseEntity<ScorePostedDto> addNewScore(@RequestBody ScoreDto scoreDto) {
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

        Optional<Score> highestScore = scoreService.getHighestScore(userOpt.get(), gameOpt.get()); // Retrieve before saving to know previous highest score

        Score score = scoreService.addScore(new Score(scoreDto.getScore(), scoreDto.getScoreImageUrl(), userOpt.get(), gameOpt.get()));
        LOGGER.info("Score added: {}", score);

        long scoreDelta;
        if (highestScore.isEmpty()) {
            scoreDelta = score.getScore();
        } else {
            scoreDelta = score.getScore() - highestScore.get().getScore();
        }

        ScorePostedDto scorePostedDto = new ScorePostedDto(scoreDto, scoreDelta, -1, score.getTimestamp()); // TODO rank

        return ResponseEntity.of(Optional.of(scorePostedDto));
    }
}
