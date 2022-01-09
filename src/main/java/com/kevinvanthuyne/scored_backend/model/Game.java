package com.kevinvanthuyne.scored_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "games")
public class Game {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Game(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Game() {
        this(-1, "");
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
}
