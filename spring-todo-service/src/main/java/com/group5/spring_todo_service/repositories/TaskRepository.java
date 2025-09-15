package com.group5.spring_todo_service.repositories;

import com.group5.spring_todo_service.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdAndDeletedFalse(Long userId);
    List<Task> findByUserIdAndDeletedTrue(Long userId);
    List<Task> findByUserIdAndIsCompleteAndDeletedFalse(Long userId, Boolean isComplete);
}