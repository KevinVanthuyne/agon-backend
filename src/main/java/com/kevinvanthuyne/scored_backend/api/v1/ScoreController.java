package com.kevinvanthuyne.scored_backend.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/scores")
public class ScoreController {

    @GetMapping
    public String getAllScores() {
        return "Hallo";
    }
}
