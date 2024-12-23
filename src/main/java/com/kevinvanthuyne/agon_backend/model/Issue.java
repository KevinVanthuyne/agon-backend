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
    private IssueStatus status;

    public Issue(Game game, User user, String description, LocalDateTime timestamp, IssueStatus status) {
        this.game = game;
        this.user = user;
        this.description = description;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Issue() {
        this(null, null, "", LocalDateTime.now(), IssueStatus.NEEDS_TRIAGE);
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

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", game=" + game.getName() +
                ", user=" + user.getName() +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}
