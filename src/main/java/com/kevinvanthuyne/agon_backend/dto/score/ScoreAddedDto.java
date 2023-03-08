package com.kevinvanthuyne.agon_backend.dto.score;

import com.kevinvanthuyne.agon_backend.dto.division.DivisionDto;

import java.time.LocalDateTime;

public record ScoreAddedDto(DivisionDto division,
                            long scoreDelta,
                            int rank,
                            int amountOfHighScores,
                            LocalDateTime timestamp) {
}
