package com.kevinvanthuyne.agon_backend.dto;

import java.time.LocalDateTime;

public record IssueDto(Long id,
                       int gameId,
                       String username,
                       String description,
                       LocalDateTime timestamp) {
}
