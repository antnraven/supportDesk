package com.example.user.service.impl;

import com.example.user.entities.User;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Async
    @Override
    public void save(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Ошибка сохранения данных о пользователе: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Async
    @Override
    public CompletableFuture<Page<User>> findAll() {
        try {
            Pageable pageable = PageRequest.of(0, 10, Sort.by("userFullname"));
            return CompletableFuture.completedFuture(userRepository.findAll(pageable));
        } catch (Exception e) {
            log.error("Ошибка получения данных обо всех пользователях: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Async
    @Override
    public CompletableFuture<User> findById(Long id) {
        try {
            return CompletableFuture.completedFuture(userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь " + id + " не найден")));
        } catch (Exception e) {
            log.error("Ошибка получения данных: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Async
    @Override
    public void updateIncident(User user) {
        try {
            if (userRepository.existsById(user.getId())) {
                userRepository.save(user);
            } else {
                throw new RuntimeException("Пользователь " + user.getId() + " не найден.");
            }
        } catch (Exception e) {
            log.error("Ошибка обновления данных о пользователе: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
