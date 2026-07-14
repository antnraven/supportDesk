package com.example.user.service.impl;

import com.example.user.entities.User;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void save_shouldSaveUser() {
        User user = new User();
        userService.save(user);
        verify(userRepository).save(user);
    }

    @Test
    void save_shouldThrowRuntimeException_onError() {
        User user = new User();
        doThrow(new RuntimeException("DB error")).when(userRepository).save(any());
        assertThrows(RuntimeException.class, () -> userService.save(user));
    }

    @Test
    void findAll_shouldReturnPageOfUsers() throws Exception {
        User user = new User();
        Page<User> page = new PageImpl<>(List.of(user));
        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);

        CompletableFuture<Page<User>> result = userService.findAll();

        assertEquals(1, result.get().getContent().size());
        verify(userRepository).findAll(any(Pageable.class));
    }

    @Test
    void findAll_shouldThrowRuntimeException_onError() {
        when(userRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> userService.findAll());
    }

    @Test
    void findById_shouldReturnUser() throws Exception {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        CompletableFuture<User> result = userService.findById(1L);

        assertEquals(1L, result.get().getId());
    }

    @Test
    void findById_shouldThrowException_whenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.findById(1L));
    }

    @Test
    void findById_shouldThrowRuntimeException_onError() {
        when(userRepository.findById(1L)).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> userService.findById(1L));
    }

    @Test
    void updateIncident_shouldUpdate_whenUserExists() {
        User user = new User();
        user.setId(1L);
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.updateIncident(user);

        verify(userRepository).save(user);
    }

    @Test
    void updateIncident_shouldThrowException_whenUserNotFound() {
        User user = new User();
        user.setId(1L);
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.updateIncident(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    void updateIncident_shouldThrowRuntimeException_onError() {
        User user = new User();
        user.setId(1L);
        when(userRepository.existsById(1L)).thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> userService.updateIncident(user));
    }
}