package com.kevinvanthuyne.agon_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "division_id")
    @JsonBackReference
    private AbstractDivision division;

    private long points;
    private String scoreImageUrl;
    private LocalDateTime timestamp;

    public Score(User user, AbstractDivision division, long points, String scoreImageUrl, LocalDateTime timestamp) {
        this.user = user;
        this.division = division;
        this.points = points;
        this.scoreImageUrl = scoreImageUrl;
        this.timestamp = timestamp;
    }

    public Score(User user, AbstractDivision division, long points) {
        this(user, division, points, "", LocalDateTime.now());
    }

    public Score() {
        this(null, null, 0);
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

    public AbstractDivision getDivision() {
        return division;
    }

    public void setDivision(AbstractDivision division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", user=" + user +
                ", division=" + division +
                ", points=" + points +
                ", scoreImageUrl='" + scoreImageUrl + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
