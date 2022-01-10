package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.Game;

public class GameDto {
    private final int id;
    private final String name;

    public GameDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GameDto(Game game) {
        this(game.getId(), game.getName());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
