package com.kevinvanthuyne.agon_backend.dto;

import com.kevinvanthuyne.agon_backend.model.Game;

public record GameDto(int id,
                      String name,
                      GameStyleDto gameStyle) implements ModelBuildable<Game> {
    public GameDto(Game game) {
        this(
                game.getId(),
                game.getName(),
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
