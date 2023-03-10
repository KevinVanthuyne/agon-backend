package com.kevinvanthuyne.agon_backend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "games")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private GameStyle gameStyle;

    public Game(int id, String name, GameStyle gameStyle) {
        this.id = id;
        this.name = name;
        this.gameStyle = gameStyle;
    }

    public Game(int id, String name) {
        this(id, name, null);
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
