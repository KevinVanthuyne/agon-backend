package com.kevinvanthuyne.agon_backend.dto;

import com.kevinvanthuyne.agon_backend.model.Game;
import com.kevinvanthuyne.agon_backend.model.GameCategory;
import com.kevinvanthuyne.agon_backend.model.GameStatus;

public record GameDto(int id,
                      String name,
                      String description,
                      String collectionHistory,
                      String howToPlay,
                      Integer year,
                      GameStatus status,
                      GameCategory category,
                      GameStyleDto gameStyle) implements ModelBuildable<Game> {
    public GameDto(Game game) {
        this(
                game.getId(),
                game.getName(),
                game.getDescription(),
                game.getCollectionHistory(),
                game.getHowToPlay(),
                game.getYear(),
                game.getStatus(),
                game.getCategory(),
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
