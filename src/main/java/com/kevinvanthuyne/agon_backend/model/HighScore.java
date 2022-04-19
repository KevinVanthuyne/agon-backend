package com.kevinvanthuyne.agon_backend.model;

public class HighScore {
    private int rank;
    private Score score;

    public HighScore(int rank, Score score) {
        this.rank = rank;
        this.score = score;
    }

    public HighScore(Score score) {
        this(-1, score);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
