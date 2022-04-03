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
                new GameStyleDto(game.getGameStyle())
        );
    }

    @Override
    public Game buildModel() {
        Game game = new Game(id, name, startDate);
        game.setGameStyle(gameStyle.buildModel(game));
        return game;
    }
}
