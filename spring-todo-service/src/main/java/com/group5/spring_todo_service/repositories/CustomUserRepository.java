package com.group5.spring_todo_service.repositories;

import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository extends JpaRepository <CustomUser, Long> {
    Optional<CustomUser> findCustomUserByEmail(String email);

    boolean existsByEmail(String email);
}
