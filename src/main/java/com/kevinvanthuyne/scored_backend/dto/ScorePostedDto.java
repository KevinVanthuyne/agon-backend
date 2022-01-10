package com.kevinvanthuyne.scored_backend.dto;

import java.time.LocalDateTime;

public class ScorePostedDto {
    private final ScoreDto score;
    private final long scoreDelta;
    private final int rank;
    private final LocalDateTime timestamp;

    public ScorePostedDto(ScoreDto score, long scoreDelta, int rank, LocalDateTime timestamp) {
        this.score = score;
        this.scoreDelta = scoreDelta;
        this.rank = rank;
        this.timestamp = timestamp;
    }

    public ScoreDto getScore() {
        return score;
    }

    public long getScoreDelta() {
        return scoreDelta;
    }

    public int getRank() {
        return rank;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
