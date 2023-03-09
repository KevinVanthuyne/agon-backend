package com.kevinvanthuyne.agon_backend.dto.score;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record AddScoreDto(@Min(0) @Max(Integer.MAX_VALUE) int divisionId,
                          @Min(0) @Max(Long.MAX_VALUE) long points,
                          @NotBlank String username) {
}
