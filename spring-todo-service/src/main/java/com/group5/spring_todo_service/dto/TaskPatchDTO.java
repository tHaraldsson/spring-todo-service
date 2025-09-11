package com.group5.spring_todo_service.dto;

public record TaskPatchDTO (

        Long id,
        String title,
        String description,
        Boolean isCompleted

){}
