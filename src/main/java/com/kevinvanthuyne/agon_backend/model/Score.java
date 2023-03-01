package com.kevinvanthuyne.agon_backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private long points;
    private String scoreImageUrl;
    private LocalDateTime timestamp;

    public Score(long points, String scoreImageUrl, User user, Game game, LocalDateTime timestamp) {
        this.points = points;
        this.scoreImageUrl = scoreImageUrl;
        this.user = user;
        this.game = game;
        this.timestamp = timestamp;
    }

    public Score(long points, String scoreImageUrl, User user, Game game) {
        this(points, scoreImageUrl, user, game, LocalDateTime.now());
    }

    public Score() {
        this(0, "", new User(), new Game(), LocalDateTime.now());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long score) {
        this.points = score;
    }

    public String getScoreImageUrl() {
        return scoreImageUrl;
    }

    public void setScoreImageUrl(String scoreImageUrl) {
        this.scoreImageUrl = scoreImageUrl;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
