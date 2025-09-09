package com.group5.spring_todo_service.repositories;

import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomUserRepository extends JpaRepository <CustomUser, Long> {
    //List<Task> findByEmail(String email);

    boolean existsByEmail(String email);
}
