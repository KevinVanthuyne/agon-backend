package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.Score;

import java.time.LocalDateTime;

public class ScoreDto {
    private final String id;
    private final long points;
    private final String scoreImageUrl;
    private final LocalDateTime timestamp;
    private final String userId;
    private final String username;
    private final String gameInitials;
    private final int gameId;

    public ScoreDto(String id,
                    long points,
                    String scoreImageUrl,
                    LocalDateTime timestamp,
                    String userId,
                    String username,
                    String gameInitials,
                    int gameId) {
        this.id = id;
        this.points = points;
        this.scoreImageUrl = scoreImageUrl;
        this.timestamp = timestamp;
        this.userId = userId;
        this.username = username;
        this.gameInitials = gameInitials;
        this.gameId = gameId;
    }

    public ScoreDto(Score score) {
        this(
                score.getId().toString(),
                score.getPoints(),
                score.getScoreImageUrl(),
                score.getTimestamp(),
                score.getUser().getId(),
                score.getUser().getName(),
                "",
                score.getGame().getId()
        );
    }

    public String getId() {
        return id;
    }

    public long getPoints() {
        return points;
    }

    public String getScoreImageUrl() {
        return scoreImageUrl;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
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
