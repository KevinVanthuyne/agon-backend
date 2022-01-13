package com.kevinvanthuyne.scored_backend.dto;

import java.time.LocalDate;

public class StartCompetitionDto {
    private final LocalDate startDate;

    public StartCompetitionDto(LocalDate startDate) {
        this.startDate = startDate;
    }

    public StartCompetitionDto() {
        this(LocalDate.EPOCH);
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
