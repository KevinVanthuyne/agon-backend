package com.kevinvanthuyne.agon_backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * TODO to be implemented
 */
@Entity
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private String description;
    private LocalDateTime timestamp;

    public Issue(Game game, String description, LocalDateTime timestamp) {
        this.game = game;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Issue() {
        this(null, "", LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
