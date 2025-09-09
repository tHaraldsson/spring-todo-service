package com.group5.spring_todo_service.controllers;

import com.group5.spring_todo_service.dto.TaskRequestDTO;
import com.group5.spring_todo_service.dto.TaskResponseDTO;
import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import com.group5.spring_todo_service.services.CustomUserService;
import com.group5.spring_todo_service.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final CustomUserService customUserService;

    public TaskController(TaskService taskService, CustomUserService customUserService) {
        this.taskService = taskService;
        this.customUserService = customUserService;
    }

    @PostMapping("/createtask")
    public ResponseEntity<TaskResponseDTO> createTask(
            @RequestBody @Valid TaskRequestDTO request,
            @RequestHeader String email,
            @RequestHeader String password) {

        CustomUser user = customUserService.authenticate(email, password).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );
        Task task = taskService.createTask(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(task.toDTO());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Task> getTask(@PathVariable Long id) {
//        return taskService.getTaskById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
}

