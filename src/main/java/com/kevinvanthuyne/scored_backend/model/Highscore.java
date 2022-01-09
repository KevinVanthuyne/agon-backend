package com.kevinvanthuyne.scored_backend.model;

public class Highscore {
    private int rank;
    private Score score;

    public Highscore(int rank, Score score) {
        this.rank = rank;
        this.score = score;
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
