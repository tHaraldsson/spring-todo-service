package com.group5.spring_todo_service;

import com.group5.spring_todo_service.repositories.CustomUserRepository;
import models.CustomUser;
import org.springframework.stereotype.Service;


@Service
public class CustomUserService {

    private final CustomUserRepository customUserRepository;

    public CustomUserService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }


}
