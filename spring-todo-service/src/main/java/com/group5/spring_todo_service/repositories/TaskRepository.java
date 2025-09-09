package com.group5.spring_todo_service.repositories;

import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findAllTasksByUserEquals(CustomUser user);
}
