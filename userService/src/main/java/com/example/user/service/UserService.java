package com.example.user.service;

import com.example.user.entities.User;
import org.springframework.data.domain.Page;

import java.util.concurrent.CompletableFuture;

public interface UserService {

    void save(User user);

    CompletableFuture<Page<User>> findAll();

    CompletableFuture<User> findById(Long id);

    void updateIncident(User user);
}
