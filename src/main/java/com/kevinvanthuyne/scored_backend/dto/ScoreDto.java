package com.kevinvanthuyne.scored_backend.dto;

public class ScoreDto {
    private final long score;
    private final String scoreImageUrl;
    private final long userId;
    private final String username;
    private final String gameInitials;
    private final int gameId;

    public ScoreDto(long score, String scoreImageUrl, long userId, String username, String gameInitials, int gameId) {
        this.score = score;
        this.scoreImageUrl = scoreImageUrl;
        this.userId = userId;
        this.username = username;
        this.gameInitials = gameInitials;
        this.gameId = gameId;
    }

    public long getScore() {
        return score;
    }

    public String getScoreImageUrl() {
        return scoreImageUrl;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public int getGameId() {
        return gameId;
    }

    public String getGameInitials() {
        return gameInitials;
    }
}
