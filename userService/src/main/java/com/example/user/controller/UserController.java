package com.example.user.controller;

import com.example.user.service.impl.UserServiceImpl;
import com.example.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(@Autowired UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addIncident(@RequestBody User user) {
        try {
            userService.save(user);
            return ResponseEntity.ok("Успешное сохранение инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PatchMapping
    public ResponseEntity<?> updateIncident(@RequestBody User user) {
        try {
            userService.updateIncident(user);
            return ResponseEntity.ok("Успешное обновление инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
