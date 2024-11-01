package com.kevinvanthuyne.agon_backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime timestamp;

    public Issue(Game game, User user, String description, LocalDateTime timestamp) {
        this.game = game;
        this.user = user;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Issue() {
        this(null, null, "", LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", game=" + game +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
