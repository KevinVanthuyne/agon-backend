package com.kevinvanthuyne.scored_backend.dto;

import java.time.LocalDateTime;

public record ScoreAddedDto(ScoreDto score,
                            GameDto game,
                            long scoreDelta,
                            int rank,
                            int amountOfHighScores,
                            LocalDateTime timestamp) {
}
