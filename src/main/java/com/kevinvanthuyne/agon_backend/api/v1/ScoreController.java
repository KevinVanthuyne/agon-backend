package com.kevinvanthuyne.agon_backend.api.v1;

import com.kevinvanthuyne.agon_backend.dto.division.DivisionDto;
import com.kevinvanthuyne.agon_backend.dto.score.ScoreAddedDto;
import com.kevinvanthuyne.agon_backend.dto.score.ScoreDto;
import com.kevinvanthuyne.agon_backend.model.Score;
import com.kevinvanthuyne.agon_backend.model.User;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;
import com.kevinvanthuyne.agon_backend.service.ScoreService;
import com.kevinvanthuyne.agon_backend.service.UserService;
import com.kevinvanthuyne.agon_backend.service.division.DivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreController.class);
    private final ScoreService scoreService;
    private final UserService userService;
    private final DivisionService divisionService;

    @Autowired
    public ScoreController(ScoreService scoreService, UserService userService, DivisionService divisionService) {
        this.scoreService = scoreService;
        this.userService = userService;
        this.divisionService = divisionService;
    }

    /**
     * Adds the given {@link Score} to the database. Creates a new {@link User} with the given username if it does not
     * exist yet.
     *
     * @return The added score with extra information like score delta.
     */
    @PostMapping
    public ResponseEntity<?> addScore(@RequestBody @Valid ScoreDto scoreDto) {
        Optional<AbstractDivision> divisionOpt = divisionService.get(scoreDto.divisionId());
        if (divisionOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Division could not be found.");
        }
        AbstractDivision division = divisionOpt.get();

        User user = userService.getOrCreateUser(scoreDto.username());
        Optional<Score> highestScore = scoreService.getHighestScore(user, division); // Retrieve before saving to know previous highest score

        // Only higher scores than a user's top score can be added
        if (highestScore.isPresent() && scoreDto.points() <= highestScore.get().getPoints()) {
            return ResponseEntity.badRequest().body("New score can only be higher than the user's current high score.");
        }

        Score newScore = new Score(user, division, scoreDto.points());
        Score score = scoreService.addScore(newScore);
        LOGGER.info("Score added: {}", score);

        long scoreDelta;
        if (highestScore.isEmpty()) {
            scoreDelta = score.getPoints();
        } else {
            scoreDelta = score.getPoints() - highestScore.get().getPoints();
        }

        ScoreAddedDto scoreAddedDto = new ScoreAddedDto(
                new DivisionDto(division),
                scoreDelta,
                scoreService.getRankOfScore(score),
                scoreService.getAmountOfScores(division),
                score.getTimestamp());

        return ResponseEntity.ok(scoreAddedDto);
    }

    /**
     * @return Map of all enabled division ids and the highest scores per user, sorted by highest score first.
     */
    @GetMapping("/active")
    public ResponseEntity<Map<Integer, List<ScoreDto>>> getSortedScoresOfActiveDivisions() {
        Map<Integer, List<ScoreDto>> scoreDtos = new HashMap<>();
        Map<Integer, List<Score>> scores = scoreService.getSortedScoresOfAllActiveDivisions();

        for (int divisionId : scores.keySet()) {
            List<Score> scoresOfDivision = scores.get(divisionId);
            List<ScoreDto> scoreDtosOfDivision = scoresOfDivision.stream().map(ScoreDto::new).toList();
            scoreDtos.put(divisionId, scoreDtosOfDivision);
        }

        return ResponseEntity.ok(scoreDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreDto> getScore(@PathVariable String id) {
        Optional<Score> scoreOpt = scoreService.getScore(UUID.fromString(id));
        if (scoreOpt.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ScoreDto(scoreOpt.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScoreDto> deleteScore(@PathVariable String id) {
        Optional<Score> scoreOpt = scoreService.getScore(UUID.fromString(id));

        if (scoreOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        scoreService.deleteScore(UUID.fromString(id));

        return ResponseEntity.ok(new ScoreDto(scoreOpt.get()));
    }

    /**
     * @return {@link List} of all {@link ScoreDto}s for the given division or an error message {@link String} if something goes wrong.
     */
    @GetMapping("/divisions/{divisionId}")
    public ResponseEntity<?> getAllScoresOfDivision(@PathVariable int divisionId) {
        Optional<AbstractDivision> divisionOptional = divisionService.get(divisionId);
        if (divisionOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Could not find division.");
        }

        List<ScoreDto> scores = divisionOptional.get().getScores().stream()
                .map(ScoreDto::new)
                .toList();

        return ResponseEntity.ok(scores);
    }

    // TODO still needed?
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ScoreDto>> getScoresOfUser(@PathVariable String userId) {
        Optional<User> userOpt = userService.getUser(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ScoreDto> scores = scoreService.getScores(userOpt.get()).stream()
                .map(ScoreDto::new)
                .toList();

        return ResponseEntity.ok(scores);
    }

    // TODO probably refactor
//    @GetMapping("/user/{userId}/personal-best")
//    public ResponseEntity<ScoreDto> getPersonalBestOfUser(@PathVariable String userId) {
//        Optional<User> userOpt = userService.getUser(userId);
//        Optional<Game> gameOpt = divisionService.getActiveGame();
//        if (userOpt.isEmpty() || gameOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Optional<Score> scoreOpt = scoreService.getHighestScore(userOpt.get(), gameOpt.get());
//        if (scoreOpt.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(new ScoreDto(scoreOpt.get()));
//    }
}
