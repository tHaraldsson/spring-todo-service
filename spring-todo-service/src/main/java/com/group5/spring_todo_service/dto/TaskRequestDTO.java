package com.group5.spring_todo_service.dto;


import jakarta.validation.constraints.NotBlank;

public record TaskRequestDTO(
        @NotBlank
        String title,
        String description

) {
}
