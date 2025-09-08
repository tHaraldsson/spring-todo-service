package com.group5.spring_todo_service.repositories;

import models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository <CustomUser, Long> {


}
