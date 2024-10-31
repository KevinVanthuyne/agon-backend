package com.kevinvanthuyne.agon_backend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "games")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private GameStyle gameStyle;

    /**
     * Generic description of the game.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Description of how the game got into the collection, where it's from, if there was any work done on it, ...
     */
    @Column(columnDefinition = "TEXT")
    private String collectionHistory;

    private Integer year;
    private String name;
    private GameCategory category;
    private GameStatus status;

    public Game(int id,
                String name,
                GameStyle gameStyle,
                String description,
                GameCategory category,
                String collectionHistory,
                GameStatus status,
                int year) {
        this.id = id;
        this.name = name;
        this.gameStyle = gameStyle;
        this.description = description;
        this.category = category;
        this.collectionHistory = collectionHistory;
        this.status = status;
        this.year = year;
    }

    public Game(String name, String description, GameCategory category) {
        this(-1, name, null, description, category, null, GameStatus.UNKNOWN, 0);
    }

    public Game(int id, String name) {
        this(id, name, null, null, null, null, GameStatus.UNKNOWN, 0);
    }

    public Game(String name) {
        this(-1, name);
    }

    public Game() {
        this("");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameStyle getGameStyle() {
        return gameStyle;
    }

    public void setGameStyle(GameStyle gameStyle) {
        this.gameStyle = gameStyle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public String getCollectionHistory() {
        return collectionHistory;
    }

    public void setCollectionHistory(String collectionHistory) {
        this.collectionHistory = collectionHistory;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
