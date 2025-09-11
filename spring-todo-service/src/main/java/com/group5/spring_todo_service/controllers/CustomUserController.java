package com.group5.spring_todo_service.controllers;


import com.group5.spring_todo_service.dto.CustomUserRegistrationDTO;
import com.group5.spring_todo_service.dto.TaskRequestDTO;
import com.group5.spring_todo_service.dto.TaskResponseDTO;
import com.group5.spring_todo_service.repositories.CustomUserRepository;
import com.group5.spring_todo_service.services.CustomUserService;
import jakarta.validation.Valid;
import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/users")
public class CustomUserController {

    @Autowired
    private final CustomUserService customUserService;
    @Autowired
    private CustomUserRepository customUserRepository;


    public CustomUserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @GetMapping("/gettasksbyuserid/{id}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(
            @PathVariable Long id,
            @RequestHeader String email,
            @RequestHeader String password) {

        customUserService.authenticate(email, password).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );

            List<TaskResponseDTO> tasks = customUserService.getTasksByUserId(id);

            if (tasks.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(tasks);
        }


        @PostMapping("/createuser")
        public ResponseEntity<CustomUser> createUser (@RequestBody @Valid CustomUserRegistrationDTO dto){

            CustomUser saveUser = customUserService.createUser(dto);
            return ResponseEntity.status(201).body(saveUser);

        }
    }
