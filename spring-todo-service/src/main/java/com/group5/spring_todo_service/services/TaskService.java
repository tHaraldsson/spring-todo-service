package com.group5.spring_todo_service.services;

import com.group5.spring_todo_service.dto.TaskPatchDTO;
import com.group5.spring_todo_service.dto.TaskCreateRequestDTO;
import com.group5.spring_todo_service.dto.TaskResponseDTO;
import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import com.group5.spring_todo_service.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task createTask(TaskCreateRequestDTO request, CustomUser user) {

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

    public List<TaskResponseDTO> getNotCompletedTasksForUser(Long userId) {

        return taskRepository.findByUserIdAndIsCompleteAndDeletedFalse(userId, false).stream()
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

    public List<TaskResponseDTO> getCompletedTasksForUser(Long userId) {

        return taskRepository.findByUserIdAndIsCompleteAndDeletedFalse(userId, true).stream()
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

    public List<TaskResponseDTO> getDeletedTasksForUser(Long userId) {
        return taskRepository.findByUserIdAndDeletedTrue(userId).stream()
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

    public Task softDeleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        task.setDeleted(true);
        taskRepository.save(task);
        return task;
    }

    public void hardDeleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        if (!task.isDeleted()) {
            throw new IllegalArgumentException("Task with id " + id + " is not marked for deletion");
        }
        taskRepository.delete(task);
    }

    public Task restoreTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        task.setDeleted(false);
        taskRepository.save(task);
        return task;
    }
}
