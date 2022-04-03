package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.Game;

import java.time.LocalDate;

public record GameDto(int id,
                      String name,
                      LocalDate startDate,
                      GameStyleDto gameStyle) implements ModelBuildable<Game> {
    public GameDto(Game game) {
        this(
                game.getId(),
                game.getName(),
                game.getStartDate(),
                game.getGameStyle() == null ? new GameStyleDto(game.getId()) : new GameStyleDto(game.getGameStyle())
        );
    }

    @Override
    public Game buildModel() {
        return new Game(id, name, startDate);
    }
}
