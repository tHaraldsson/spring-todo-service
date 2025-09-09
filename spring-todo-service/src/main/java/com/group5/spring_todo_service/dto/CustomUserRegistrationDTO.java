package com.group5.spring_todo_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomUserRegistrationDTO(
        @NotBlank(message = "Email cannot be empty!")
        @Email
        String email,

        @NotBlank(message = "Password must not be empty!")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "The password must contain at least 8 chars, a capital letter and a number")
        String password,

        @NotBlank(message = "Role cannot be empty!")
        String role

) {}
