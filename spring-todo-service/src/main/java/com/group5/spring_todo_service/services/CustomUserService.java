package com.group5.spring_todo_service.services;

import com.group5.spring_todo_service.dto.CustomUserRegistrationDTO;
import com.group5.spring_todo_service.dto.TaskResponseDTO;
import com.group5.spring_todo_service.repositories.CustomUserRepository;
import com.group5.spring_todo_service.models.CustomUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class CustomUserService {

    private final CustomUserRepository customUserRepository;

    public CustomUserService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }


    public List<TaskResponseDTO> getTasksByUserId(Long userId) {

        return customUserRepository.findById(userId)
                .map(user -> user.getTasks().stream()
                        .map(task -> new TaskResponseDTO(
                                task.getId(),
                                task.getTitle(),
                                task.getDescription(),
                                task.isComplete(),
                                task.isDeleted(),
                                task.getUser().getEmail()
                        ))
                        .toList()
                )
                .orElseGet(Collections::emptyList);
    }

    public CustomUser createUser(CustomUserRegistrationDTO dto) {

        if (customUserRepository.existsByEmail(dto.email())){
        throw new IllegalArgumentException("Email already exists");
    }

    CustomUser savedUser = new CustomUser();
        savedUser.setEmail(dto.email());
        savedUser.setPassword(dto.password());
        savedUser.setRole("USER");
        savedUser.setTasks(new ArrayList<>());
        return customUserRepository.save(savedUser);
    }

    public CustomUser createAdmin(CustomUserRegistrationDTO dto) {

        if (customUserRepository.existsByEmail(dto.email())){
            throw new IllegalArgumentException("Email already exists");
        }

        CustomUser savedUser = new CustomUser();
        savedUser.setEmail(dto.email());
        savedUser.setPassword(dto.password());
        savedUser.setRole("ADMIN");
        savedUser.setTasks(new ArrayList<>());
        return customUserRepository.save(savedUser);
    }

    public void deleteUser(Long userId) {
        CustomUser user = customUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));;

        customUserRepository.deleteById(userId);
    }

}
