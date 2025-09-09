package com.group5.spring_todo_service.services;

import com.group5.spring_todo_service.repositories.TaskRepository;
import models.CustomUser;
import models.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

}
