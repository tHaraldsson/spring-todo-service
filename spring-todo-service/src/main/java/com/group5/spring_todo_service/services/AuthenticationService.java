package com.group5.spring_todo_service.services;

import com.group5.spring_todo_service.models.CustomUser;
import com.group5.spring_todo_service.repositories.CustomUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AuthenticationService {

    private final CustomUserRepository customUserRepository;

    public AuthenticationService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    };


    public CustomUser authenticateOrThrow (String email, String password) {

        return customUserRepository.findCustomUserByEmail(email)
                .filter(customUser -> customUser.getPassword().equals(password)).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

}
