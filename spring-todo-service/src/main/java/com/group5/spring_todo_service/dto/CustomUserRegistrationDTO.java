package com.group5.spring_todo_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomUserRegistrationDTO(
        @NotBlank(message = "Email cannot be empty!")
        @Email
        String email,

        @NotBlank(message = "Password must not be empty!")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z0-9!@#$%^&*()_+=-]{8,}$",
                message = "1. Password must be at least 8 chars 2. Must contain a capital letter and a number. 3. May only contain A-Z, !@#$%^&*()_+=-")
        String password

) {}
