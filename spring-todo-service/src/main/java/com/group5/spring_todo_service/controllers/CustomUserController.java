package com.group5.spring_todo_service.controllers;


import com.group5.spring_todo_service.dto.CustomUserRegistrationDTO;
import com.group5.spring_todo_service.repositories.CustomUserRepository;
import com.group5.spring_todo_service.services.CustomUserService;
import jakarta.validation.Valid;
import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/gettasksbyid/{id}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long id){
       List<Task> tasks = customUserService.getTasksByUserId(id);

        if (tasks!=null) {
            return  ResponseEntity.ok(tasks);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createuser")
    public ResponseEntity<CustomUser> createUser(@RequestBody @Valid CustomUserRegistrationDTO dto) {

        CustomUser saveUser = customUserService.createUser(dto);
        return ResponseEntity.ok(saveUser);

    }
}
