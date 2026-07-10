package com.example.user.service;

import com.example.user.entities.User;
import com.example.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Ошибка сохранения данных о пользователе: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Page<User> findAll() {
        try {
            Pageable pageable = PageRequest.of(0, 10, Sort.by("userFullname"));
            return userRepository.findAll(pageable);
        } catch (Exception e) {
            log.error("Ошибка получения данных обо всех пользователях: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public User findById(Long id) {
        try {
            return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь " + id + " не найден"));
        } catch (Exception e) {
            log.error("Ошибка получения данных: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

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
