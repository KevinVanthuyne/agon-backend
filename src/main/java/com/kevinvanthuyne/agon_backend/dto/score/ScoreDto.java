package com.kevinvanthuyne.agon_backend.dto.score;

import com.kevinvanthuyne.agon_backend.model.Score;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

public record ScoreDto(String id,
                       @Min(0) @Max(Long.MAX_VALUE) long points,
                       String scoreImageUrl,
                       LocalDateTime timestamp,
                       String userId,
                       @Length(min = 3, max = 32) String username,
                       String gameInitials,
                       @Min(0) @Max(Integer.MAX_VALUE) int divisionId) {
    public ScoreDto(Score score) {
        this(
                score.getId().toString(),
                score.getPoints(),
                score.getScoreImageUrl(),
                score.getTimestamp(),
                score.getUser().getId(),
                score.getUser().getName(),
                score.getUser().getInitials(),
                score.getDivision().getId()
        );
    }
}
