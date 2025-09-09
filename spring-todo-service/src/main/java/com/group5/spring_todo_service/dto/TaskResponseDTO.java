package com.group5.spring_todo_service.dto;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        boolean isComplete,
        String userEmail
) {
}
