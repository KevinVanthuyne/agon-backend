package com.kevinvanthuyne.scored_backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {
    @Id
    private long discordId;
    private String name;

    public User(int discordId, String name) {
        this.discordId = discordId;
        this.name = name;
    }

    public User() {
        this(-1, "");
    }

    public long getDiscordId() {
        return discordId;
    }

    public void setDiscordId(long discordId) {
        this.discordId = discordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
