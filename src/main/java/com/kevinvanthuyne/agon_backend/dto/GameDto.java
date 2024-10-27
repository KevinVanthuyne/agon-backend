package com.kevinvanthuyne.agon_backend.dto;

import com.kevinvanthuyne.agon_backend.model.Game;

public record GameDto(int id,
                      String name,
                      String description,
                      GameStyleDto gameStyle) implements ModelBuildable<Game> {
    public GameDto(Game game) {
        this(
                game.getId(),
                game.getName(),
                game.getDescription(),
                new GameStyleDto(game.getGameStyle())
        );
    }

    @Override
    public Game buildModel() {
        Game game = new Game(id, name);
        game.setGameStyle(gameStyle.buildModel(game));
        return game;
    }
}
