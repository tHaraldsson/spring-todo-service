package com.group5.spring_todo_service.repositories;

import models.CustomUser;
import models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomUserRepository extends JpaRepository <CustomUser, Long> {
List<Task> findByUser(CustomUser customUser);

    boolean existsByEmail(String email);
}
