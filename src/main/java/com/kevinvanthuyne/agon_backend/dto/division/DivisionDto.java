package com.kevinvanthuyne.agon_backend.dto.division;

import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;

public record DivisionDto(int id, int gameId) {
    public DivisionDto(AbstractDivision division) {
        this(division.getId(), division.getGame().getId());
    }
}
