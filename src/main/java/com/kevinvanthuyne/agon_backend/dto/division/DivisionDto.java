package com.kevinvanthuyne.agon_backend.dto.division;

import com.kevinvanthuyne.agon_backend.dto.GameDto;
import com.kevinvanthuyne.agon_backend.dto.score.ScoreDto;
import com.kevinvanthuyne.agon_backend.model.division.AbstractDivision;

import java.util.List;

public record DivisionDto(int id, GameDto game, List<ScoreDto> scores) {
    public DivisionDto(AbstractDivision division) {
        this(division.getId(), new GameDto(division.getGame()), division.getScores().stream().map(ScoreDto::new).toList());
    }
}
