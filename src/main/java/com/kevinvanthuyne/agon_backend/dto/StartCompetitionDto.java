package com.kevinvanthuyne.agon_backend.dto;

import java.time.LocalDate;

public record StartCompetitionDto(LocalDate startDate) {
    public StartCompetitionDto() {
        this(LocalDate.EPOCH);
    }
}
