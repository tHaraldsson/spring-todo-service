package com.group5.spring_todo_service.controllers;


import com.group5.spring_todo_service.dto.CustomUserRegistrationDTO;
import com.group5.spring_todo_service.dto.CustomUserResponseDTO;
import com.group5.spring_todo_service.dto.TaskResponseDTO;
import com.group5.spring_todo_service.services.AuthenticationService;
import com.group5.spring_todo_service.services.CustomUserService;
import jakarta.validation.Valid;
import com.group5.spring_todo_service.models.CustomUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class CustomUserController {


    private final CustomUserService customUserService;
    private final AuthenticationService authenticationService;

    public CustomUserController(CustomUserService customUserService, AuthenticationService authenticationService) {
        this.customUserService = customUserService;
        this.authenticationService = authenticationService;
    }


    @GetMapping("/admin/gettasksbyuserid/{id}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(
            @PathVariable Long id,
            @RequestHeader String email,
            @RequestHeader String password) {

        CustomUser user = authenticationService.authenticateOrThrow(email, password);

        if (!user.getRole().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<TaskResponseDTO> tasks = customUserService.getTasksByUserId(id);

        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tasks);
    }


    @PostMapping("/createuser")
    public ResponseEntity<CustomUserResponseDTO> createUser(@RequestBody @Valid CustomUserRegistrationDTO dto) {

        CustomUser saveUser = customUserService.createUser(dto);


        return ResponseEntity.status(201).body(saveUser.toDTO());

    }

    @PostMapping("/admin/createadmin")
    public ResponseEntity<CustomUserResponseDTO> createAdmin(
            @RequestBody @Valid CustomUserRegistrationDTO dto,
            @RequestHeader String email,
            @RequestHeader String password) {

        CustomUser user = authenticationService.authenticateOrThrow(email, password);

        if (!"ADMIN".equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        CustomUser saveUser = customUserService.createAdmin(dto);


        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser.toDTO());

    }

    @DeleteMapping("/admin/deleteuser/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(
            @PathVariable Long id,
            @RequestHeader String email,
            @RequestHeader String password
    ) {
        CustomUser user = authenticationService.authenticateOrThrow(email, password);
        if (!"ADMIN".equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        customUserService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "User with id " + id + " deleted successfully"));
    }

}