package com.group5.spring_todo_service.controllers;


import com.group5.spring_todo_service.dto.CustomUserRegistrationDTO;
import com.group5.spring_todo_service.dto.TaskResponseDTO;
import com.group5.spring_todo_service.services.AuthenticationService;
import com.group5.spring_todo_service.services.CustomUserService;
import jakarta.validation.Valid;
import com.group5.spring_todo_service.models.CustomUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/users")
public class CustomUserController {


    private final CustomUserService customUserService;
    private final AuthenticationService authenticationService;

    public CustomUserController(CustomUserService customUserService, AuthenticationService authenticationService) {
        this.customUserService = customUserService;
        this.authenticationService = authenticationService;
    }


    @GetMapping("/gettasksbyuserid/{id}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(
            @PathVariable Long id,
            @RequestHeader String email,
            @RequestHeader String password) {

        authenticationService.authenticateOrThrow(email, password);

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
