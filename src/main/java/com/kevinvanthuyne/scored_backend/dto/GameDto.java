package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.Game;

import java.time.LocalDate;

public class GameDto implements ModelBuildable<Game> {
    private final int id;
    private final String name;
    private final LocalDate startDate;

    public GameDto(int id, String name, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

    public GameDto(Game game) {
        this(game.getId(), game.getName(), game.getStartDate());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public Game buildModel() {
        return new Game(id, name, startDate);
    }
}
