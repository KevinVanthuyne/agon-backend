package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.Game;

import java.time.LocalDate;

public record GameDto(int id,
                      String name,
                      LocalDate startDate) implements ModelBuildable<Game> {
    public GameDto(Game game) {
        this(game.getId(), game.getName(), game.getStartDate());
    }

    @Override
    public Game buildModel() {
        return new Game(id, name, startDate);
    }
}
