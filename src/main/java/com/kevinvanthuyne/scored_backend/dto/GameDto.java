package com.kevinvanthuyne.scored_backend.dto;

public class GameDto {
    private final int id;
    private final String name;

    public GameDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
