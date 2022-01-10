package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.HighScore;

public class HighScoreDto {
    private final int rank;
    private final String username;
    private final long userId;
    private final long score;

    public HighScoreDto(int rank, String username, long userId, long score) {
        this.rank = rank;
        this.username = username;
        this.userId = userId;
        this.score = score;
    }

    public HighScoreDto(HighScore highScore) {
        this(
                highScore.getRank(),
                highScore.getScore().getUser().getName(),
                highScore.getScore().getUser().getId(),
                highScore.getScore().getScore()
        );
    }

    public int getRank() {
        return rank;
    }

    public String getUsername() {
        return username;
    }

    public long getUserId() {
        return userId;
    }

    public long getScore() {
        return score;
    }
}
