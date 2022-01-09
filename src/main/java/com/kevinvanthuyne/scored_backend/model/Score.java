package com.kevinvanthuyne.scored_backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "scores")
public class Score {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private long score;
    private String scoreImageUrl;
    private LocalDateTime timestamp;

    public Score(long score, String scoreImageUrl, User user, Game game, LocalDateTime timestamp) {
        this.score = score;
        this.scoreImageUrl = scoreImageUrl;
        this.user = user;
        this.game = game;
        this.timestamp = timestamp;
    }

    public Score() {
        this(0, "", new User(), new Game(), LocalDateTime.now());
    }
}
