package com.kevinvanthuyne.agon_backend.api.v1;

import com.kevinvanthuyne.agon_backend.dto.IssueDto;
import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.Issue;
import com.kevinvanthuyne.agon_backend.model.IssueStatus;
import com.kevinvanthuyne.agon_backend.model.User;
import com.kevinvanthuyne.agon_backend.service.GameService;
import com.kevinvanthuyne.agon_backend.service.IssueService;
import com.kevinvanthuyne.agon_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IssueController.class);
    private final IssueService issueService;
    private final UserService userService;
    private final GameService gameService;

    public IssueController(IssueService issueService, UserService userService, GameService gameService) {
        this.issueService = issueService;
        this.userService = userService;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<IssueDto>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues().stream()
                .map(issue ->
                        new IssueDto(issue.getId(),
                                issue.getGame().getId(),
                                issue.getUser().getName(),
                                issue.getDescription(),
                                issue.getTimestamp(),
                                issue.getStatus()))
                .toList());
    }

    @PostMapping
    public ResponseEntity<?> addIssue(@RequestBody IssueDto dto) {
        Optional<Game> gameOpt = gameService.getGame(dto.gameId());
        if (gameOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Game could not be found");
        }

        User user = userService.getOrCreateUser(dto.username());
        Issue issue = new Issue(gameOpt.get(), user, dto.description(), LocalDateTime.now(), IssueStatus.NEEDS_TRIAGE);
        issueService.addIssue(issue);
        LOGGER.info("Added issue: {}", issue);

        return ResponseEntity.ok(issue);
    }

    @PutMapping
    public ResponseEntity<?> updateIssue(@RequestBody IssueDto dto) {
        Optional<Issue> issueOptional = issueService.getIssue(dto.id());
        if (issueOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Game could not be found");
        }
        Issue issue = issueOptional.get();

        // TODO bad update
        if (dto.status() != null) {
            issue.setStatus(dto.status());
        }

        Issue updatedIssue = issueService.updateIssue(issue);
        LOGGER.info("Updated issue: {}", updatedIssue);
        return ResponseEntity.ok(updatedIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIssue(@PathVariable Long id) {
        Optional<Issue> issueOptional = issueService.getIssue(id);
        if (issueOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Issue could not be found");
        }
        issueService.deleteIssue(issueOptional.get().getId());
        LOGGER.info("Deleted issue: {}", issueOptional.get());
        return ResponseEntity.ok().build();
    }
}
