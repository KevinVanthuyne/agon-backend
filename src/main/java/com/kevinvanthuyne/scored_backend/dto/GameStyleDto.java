package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.Game;
import com.kevinvanthuyne.scored_backend.model.GameStyle;

public record GameStyleDto(int gameId,
                           String backgroundImage,
                           String backgroundColor,
                           String headerImage,
                           String borderColor,
                           String fontColor) implements ModelWithDependencyBuildable<GameStyle, Game> {

    public GameStyleDto(GameStyle gameStyle) {
        this(
                gameStyle.getGame().getId(),
                gameStyle.getBackgroundImage(),
                gameStyle.getBackgroundColor(),
                gameStyle.getHeaderImage(),
                gameStyle.getBorderColor(),
                gameStyle.getFontColor());
    }

    @Override
    public GameStyle buildModel(Game game) {
        return new GameStyle(game, backgroundImage, backgroundColor, headerImage, borderColor, fontColor);
    }
}
