package com.kevinvanthuyne.scored_backend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "game_styles")
public class GameStyle implements Serializable {
    @Id
    @Column(name = "game_id")
    private int gameId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "game_id")
    private Game game;

    private String backgroundImage;
    private String backgroundColor;
    private String headerImage;
    private String borderColor;
    private String fontColor;

    public GameStyle(Game game, String backgroundImage, String backgroundColor, String headerImage, String borderColor, String fontColor) {
        this.game = game;
        this.gameId = game.getId();
        this.backgroundImage = backgroundImage;
        this.backgroundColor = backgroundColor;
        this.headerImage = headerImage;
        this.borderColor = borderColor;
        this.fontColor = fontColor;
    }

    public GameStyle(Game game) {
        this(game, "", "gray", "", "", "orange");
    }

    public GameStyle() {
        this(new Game());
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }
}
