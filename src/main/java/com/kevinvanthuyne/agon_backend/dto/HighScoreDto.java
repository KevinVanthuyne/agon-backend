package com.kevinvanthuyne.agon_backend.dto;

import com.kevinvanthuyne.agon_backend.model.HighScore;

public record HighScoreDto(int rank, String username, String initials, String userId, long score) {
    public HighScoreDto(HighScore highScore) {
        this(
                highScore.getRank(),
                highScore.getScore().getUser().getName(),
                highScore.getScore().getUser().getInitials(),
                highScore.getScore().getUser().getId(),
                highScore.getScore().getPoints()
        );
    }
}
