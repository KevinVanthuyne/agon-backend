package com.kevinvanthuyne.scored_backend.dto;

import java.time.LocalDateTime;

public class ScoreAddedDto {
    private final ScoreDto score;
    private final GameDto game;
    private final long scoreDelta;
    private final int rank;
    private final int amountOfHighScores;
    private final LocalDateTime timestamp;

    public ScoreAddedDto(ScoreDto score, GameDto game, long scoreDelta, int rank, int amountOfHighScores, LocalDateTime timestamp) {
        this.score = score;
        this.game = game;
        this.scoreDelta = scoreDelta;
        this.rank = rank;
        this.amountOfHighScores = amountOfHighScores;
        this.timestamp = timestamp;
    }

    public ScoreDto getScore() {
        return score;
    }

    public GameDto getGame() {
        return game;
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

    public int getAmountOfHighScores() {
        return amountOfHighScores;
    }
}
