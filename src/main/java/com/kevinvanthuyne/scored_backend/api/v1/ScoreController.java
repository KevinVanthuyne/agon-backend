package com.kevinvanthuyne.scored_backend.api.v1;

import com.kevinvanthuyne.scored_backend.dto.GameDto;
import com.kevinvanthuyne.scored_backend.dto.HighScoreDto;
import com.kevinvanthuyne.scored_backend.dto.ScoreAddedDto;
import com.kevinvanthuyne.scored_backend.dto.ScoreDto;
import com.kevinvanthuyne.scored_backend.model.Game;
import com.kevinvanthuyne.scored_backend.model.HighScore;
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
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/score")
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<ScoreDto> getScore(@PathVariable String id) {
        Optional<Score> scoreOpt = scoreService.getScore(UUID.fromString(id));
        if (scoreOpt.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ScoreDto(scoreOpt.get()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ScoreDto> deleteScore(@PathVariable String id) {
        Optional<Score> scoreOpt = scoreService.getScore(UUID.fromString(id));

        if (scoreOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        scoreService.deleteScore(UUID.fromString(id));

        return ResponseEntity.ok(new ScoreDto(scoreOpt.get()));
    }

    @GetMapping(path = "/game/{gameId}/user/{userId}")
    public ResponseEntity<List<ScoreDto>> getScoresOfUserForGame(@PathVariable int gameId, @PathVariable String userId) {
        Optional<Game> gameOpt = gameService.getGame(gameId);
        Optional<User> userOpt = userService.getUser(userId);
        if (gameOpt.isEmpty() || userOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ScoreDto> scores = scoreService.getScores(gameOpt.get(), userOpt.get()).stream()
                .map(ScoreDto::new)
                .toList();

        return ResponseEntity.ok(scores);
    }

    @GetMapping(path = "/game/{gameId}")
    public ResponseEntity<List<ScoreDto>> getScoresForGame(@PathVariable int gameId) {
        Optional<Game> gameOpt = gameService.getGame(gameId);
        if (gameOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ScoreDto> scores = scoreService.getScores(gameOpt.get()).stream()
                .map(ScoreDto::new)
                .toList();

        return ResponseEntity.ok(scores);
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<List<ScoreDto>> getScoresOfUser(@PathVariable String userId) {
        Optional<User> userOpt = userService.getUser(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ScoreDto> scores = scoreService.getScores(userOpt.get()).stream()
                .map(ScoreDto::new)
                .toList();

        return ResponseEntity.ok(scores);
    }

    @GetMapping(path = "/game/{gameId}/ranking")
    public ResponseEntity<List<HighScoreDto>> getRankingOfGame(@PathVariable int gameId) {
        Optional<Game> gameOpt = gameService.getGame(gameId);
        if (gameOpt.isEmpty()) {
            LOGGER.info("Could not find game id {}", gameId);
            return ResponseEntity.badRequest().build();
        }

        List<HighScoreDto> highScores = scoreService.getRanking(gameOpt.get()).stream()
                .map(HighScoreDto::new)
                .toList();

        return ResponseEntity.ok(highScores);
    }

    @GetMapping(path = "/ranking")
    public ResponseEntity<List<HighScoreDto>> getRankingOfCurrentGame() {
        Optional<Game> activeGameOpt = gameService.getActiveGame();

        if (activeGameOpt.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        List<HighScoreDto> highScores = scoreService.getRanking(activeGameOpt.get()).stream()
                .map(HighScoreDto::new)
                .toList();

        return ResponseEntity.ok(highScores);
    }

    @PostMapping
    public ResponseEntity<ScoreAddedDto> addNewScore(@RequestBody ScoreDto scoreDto) {
        Optional<Game> activeGameOpt = gameService.getActiveGame();
        if (activeGameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> userOpt = userService.getUser(scoreDto.getUserId());
        if (userOpt.isEmpty()) {
            User user = userService.addUser(new User(scoreDto.getUserId(), scoreDto.getUsername(), ""));
            LOGGER.info("New user added: {}", user);
            userOpt = Optional.of(user);
        }

        Optional<Score> highestScore = scoreService.getHighestScore(userOpt.get(), activeGameOpt.get()); // Retrieve before saving to know previous highest score

        // Only higher scores than a user's top score can be added
        if (highestScore.isPresent() && scoreDto.getPoints() <= highestScore.get().getPoints()) {
            return ResponseEntity.badRequest().build();
        }

        Score score = scoreService.addScore(new Score(scoreDto.getPoints(), scoreDto.getScoreImageUrl(), userOpt.get(), activeGameOpt.get()));
        LOGGER.info("Score added: {}", score);

        long scoreDelta;
        if (highestScore.isEmpty()) {
            scoreDelta = score.getPoints();
        } else {
            scoreDelta = score.getPoints() - highestScore.get().getPoints();
        }

        HighScore highScore = scoreService.getRankOfScore(score).orElseThrow(() -> new IllegalArgumentException("Added scores should always be a high score."));
        int highscoreAmount = scoreService.getUnrankedHighScoresPerUser(activeGameOpt.get()).size();

        ScoreAddedDto scoreAddedDto = new ScoreAddedDto(scoreDto,
                                                        new GameDto(activeGameOpt.get()),
                                                        scoreDelta, highScore.getRank(),
                                                        highscoreAmount,
                                                        score.getTimestamp());

        return ResponseEntity.ok(scoreAddedDto);
    }
}
