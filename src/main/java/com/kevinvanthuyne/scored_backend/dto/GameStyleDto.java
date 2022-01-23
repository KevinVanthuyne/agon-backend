package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.GameStyle;

public record GameStyleDto(int gameId,
                           String backgroundImage,
                           String backgroundColor,
                           String headerImage,
                           String borderColor,
                           String fontColor) {
    public GameStyleDto(GameStyle gameStyle) {
        this(
                gameStyle.getGame().getId(),
                gameStyle.getBackgroundImage(),
                gameStyle.getBackgroundColor(),
                gameStyle.getHeaderImage(),
                gameStyle.getBorderColor(),
                gameStyle.getFontColor());
    }
}
