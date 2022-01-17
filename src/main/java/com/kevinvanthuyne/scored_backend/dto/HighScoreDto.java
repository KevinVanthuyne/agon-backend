package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.HighScore;

public class HighScoreDto {
    private final int rank;
    private final String username;
    private final String initials;
    private final String userId;
    private final long score;

    public HighScoreDto(int rank, String username, String initials, String userId, long score) {
        this.rank = rank;
        this.username = username;
        this.initials = initials;
        this.userId = userId;
        this.score = score;
    }

    public HighScoreDto(HighScore highScore) {
        this(
                highScore.getRank(),
                highScore.getScore().getUser().getName(),
                highScore.getScore().getUser().getInitials(),
                highScore.getScore().getUser().getId(),
                highScore.getScore().getPoints()
        );
    }

    public int getRank() {
        return rank;
    }

    public String getUsername() {
        return username;
    }

    public String getInitials() {
        return initials;
    }

    public String getUserId() {
        return userId;
    }

    public long getScore() {
        return score;
    }
}
