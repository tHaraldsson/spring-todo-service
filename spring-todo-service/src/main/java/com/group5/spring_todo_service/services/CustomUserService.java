package com.group5.spring_todo_service.services;

import com.group5.spring_todo_service.dto.CustomUserRegistrationDTO;
import com.group5.spring_todo_service.repositories.CustomUserRepository;
import models.CustomUser;
import models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomUserService {

    private final CustomUserRepository customUserRepository;

    public CustomUserService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    public List<Task> getTasksByUserId(Long userId) {

        Optional<CustomUser> user = customUserRepository.findById(userId);

        if(user.isPresent()) {
            return  user.get().getTasks();
        }
        return null;
    }

    public CustomUser createUser(CustomUserRegistrationDTO dto) {

        if (customUserRepository.existsByEmail(dto.email())){
        throw new IllegalArgumentException("Email already exists");
    }

    CustomUser savedUser = new CustomUser();
        savedUser.setEmail(dto.email());
        savedUser.setPassword(dto.password());
        savedUser.setRole(dto.role());
        savedUser.setTasks(new ArrayList<>());
        return customUserRepository.save(savedUser);
    }



}
