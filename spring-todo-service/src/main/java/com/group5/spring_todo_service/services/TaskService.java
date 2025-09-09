package com.group5.spring_todo_service.services;

import com.group5.spring_todo_service.dto.TaskRequestDTO;
import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import com.group5.spring_todo_service.repositories.TaskRepository;
import org.springframework.stereotype.Service;

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
        task.setUser(user);
        return taskRepository.save(task);
    }
}
