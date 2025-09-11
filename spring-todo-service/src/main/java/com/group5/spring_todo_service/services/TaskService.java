package com.group5.spring_todo_service.services;

import com.group5.spring_todo_service.dto.TaskPatchDTO;
import com.group5.spring_todo_service.dto.TaskRequestDTO;
import com.group5.spring_todo_service.dto.TaskResponseDTO;
import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import com.group5.spring_todo_service.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task createTask(TaskRequestDTO request, CustomUser user) {

        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setComplete(false);
        task.setDeleted(false);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task patchTask(TaskPatchDTO request) {
        Task task = taskRepository.findById(request.id()).orElseThrow(
                () -> new RuntimeException("Task with id " + request.id() + " not found")
        );


        if (request.title() != null) {
            task.setTitle(request.title());
        }

        if (request.description() != null) {
            task.setDescription(request.description());
        }

        if (request.isCompleted() != null) {
            task.setComplete(request.isCompleted());
        }
        return taskRepository.save(task);
    }

    public List<TaskResponseDTO> getActiveTasksForUser(Long userId) {

        return taskRepository.findByUserIdAndDeletedFalse(userId).stream()
                .map(task -> new TaskResponseDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isComplete(),
                        task.isDeleted(),
                        task.getUser().getEmail()
                ))
                .toList();

    }
}
