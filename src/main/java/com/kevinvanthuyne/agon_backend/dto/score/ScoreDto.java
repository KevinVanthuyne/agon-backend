package com.kevinvanthuyne.agon_backend.dto.score;

import com.kevinvanthuyne.agon_backend.model.Score;

import java.time.LocalDateTime;

public record ScoreDto(String id,
                       long points,
                       String scoreImageUrl,
                       LocalDateTime timestamp,
                       String userId,
                       String username,
                       String gameInitials,
                       int divisionId) {
    public ScoreDto(Score score) {
        this(
                score.getId().toString(),
                score.getPoints(),
                score.getScoreImageUrl(),
                score.getTimestamp(),
                score.getUser().getId(),
                score.getUser().getName(),
                score.getUser().getInitials(),
                score.getDivision().getId()
        );
    }
}
