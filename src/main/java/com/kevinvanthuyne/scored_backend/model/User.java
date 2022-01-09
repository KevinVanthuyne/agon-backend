package com.kevinvanthuyne.scored_backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {
    @Id
    private long id;
    private String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
        this(-1, "");
    }

    public long getId() {
        return id;
    }

    public void setId(long discordId) {
        this.id = discordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
