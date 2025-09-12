package com.group5.spring_todo_service.controllers;

import com.group5.spring_todo_service.dto.TaskPatchDTO;
import com.group5.spring_todo_service.dto.TaskCreateRequestDTO;
import com.group5.spring_todo_service.dto.TaskResponseDTO;
import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import com.group5.spring_todo_service.repositories.TaskRepository;
import com.group5.spring_todo_service.services.AuthenticationService;
import com.group5.spring_todo_service.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final AuthenticationService authenticationService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, AuthenticationService authenticationService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.authenticationService = authenticationService;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/createtask")
    public ResponseEntity<TaskResponseDTO> createTask(
            @RequestBody @Valid TaskCreateRequestDTO request,
            @RequestHeader String email,
            @RequestHeader String password) {

        CustomUser user = authenticationService.authenticateOrThrow(email, password);

        Task task = taskService.createTask(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(task.toDTO());
    }

    @PatchMapping("/patchtask")
    public ResponseEntity<TaskResponseDTO> patchTask(

            @RequestBody @Valid TaskPatchDTO request,
            @RequestHeader String email,
            @RequestHeader String password) {

        CustomUser user = authenticationService.authenticateOrThrow(email, password);

        Optional<Task> optionalTask = taskRepository.findById(request.id());

        if (!(user.equals(optionalTask.get().getUser()))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Task task = taskService.patchTask(request);

        return ResponseEntity.ok(task.toDTO());
    }

    @GetMapping("/mytasks")
    public ResponseEntity<List<TaskResponseDTO>> getMyTasks(
            @RequestHeader String email,
            @RequestHeader String password) {

        CustomUser user = authenticationService.authenticateOrThrow(email, password);

        List<TaskResponseDTO> tasks = taskService.getActiveTasksForUser(user.getId());

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/mytrashcan")
    public ResponseEntity<List<TaskResponseDTO>> getMyTrashCan(
            @RequestHeader String email,
            @RequestHeader String password) {

        CustomUser user = authenticationService.authenticateOrThrow(email, password);

        List<TaskResponseDTO> tasks = taskService.getDeletedTasksForUser(user.getId());

        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/softdelete/{id}")
    public ResponseEntity<TaskResponseDTO> softDelete(
            @PathVariable Long id,
            @RequestHeader String email,
            @RequestHeader String password) {

        CustomUser user = authenticationService.authenticateOrThrow(email, password);

        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!(user.equals(optionalTask.get().getUser()))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Task task = taskService.softDeleteTask(id);

        return ResponseEntity.ok(task.toDTO());
    }

    @DeleteMapping("/harddelete/{id}")
    public ResponseEntity<Void> hardDelete(
            @PathVariable Long id,
            @RequestHeader String email,
            @RequestHeader String password
    ) {
        CustomUser user = authenticationService.authenticateOrThrow(email, password);

        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!(user.equals(optionalTask.get().getUser()))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        taskService.hardDeleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/restoretask/{id}")
    public ResponseEntity<TaskResponseDTO> restoreTask(
            @PathVariable Long id,
            @RequestHeader String email,
            @RequestHeader String password) {

        CustomUser user = authenticationService.authenticateOrThrow(email, password);

        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!(user.equals(optionalTask.get().getUser()))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Task task = taskService.restoreTask(id);
        return ResponseEntity.ok(task.toDTO());
    }

}

