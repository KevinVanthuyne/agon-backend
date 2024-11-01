package com.kevinvanthuyne.agon_backend.dto;

public record IssueDto(Long id,
                       int gameId,
                       String username,
                       String description) {
}
