package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.Game;

import java.time.LocalDate;

public class GameDto {
    private final int id;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public GameDto(int id, String name, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public GameDto(Game game) {
        this(game.getId(), game.getName(), game.getStartDate(), game.getEndDate());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
