package com.kevinvanthuyne.scored_backend.api.v1;

import com.kevinvanthuyne.scored_backend.dto.StartCompetitionDto;
import com.kevinvanthuyne.scored_backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/competition")
public class CompetitionController {

    private final GameService gameService;

    @Autowired
    public CompetitionController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<StartCompetitionDto> startCompetition(@RequestBody StartCompetitionDto startDto) {
        gameService.setStartDates(startDto.startDate());
        return ResponseEntity.ok(startDto);
    }
}
