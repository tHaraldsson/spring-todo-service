package com.group5.spring_todo_service.dto;

public record CustomUserResponseDTO(
        Long id,
        String email,
        String role
) {}
