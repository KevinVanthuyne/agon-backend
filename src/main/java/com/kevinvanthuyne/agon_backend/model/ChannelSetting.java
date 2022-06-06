package com.kevinvanthuyne.agon_backend.model;

public enum ChannelSetting {
    SCORING("scoring"),
    HALL_OF_FAME("hall-of-fame"),
    GAME_ANNOUNCEMENT("game-announcement");

    private final String string;

    ChannelSetting(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
