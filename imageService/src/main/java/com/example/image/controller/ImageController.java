package com.example.image.controller;

import com.example.image.entities.Image;
import com.example.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(@Autowired ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<?> addIncident(@RequestBody Image image) {
        try {
            imageService.save(image);
            return ResponseEntity.ok("Успешное сохранение инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(imageService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(imageService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PatchMapping
    public ResponseEntity<?> updateIncident(@RequestBody Image image {
        try {
            imageService.updateIncident(image);
            return ResponseEntity.ok("Успешное обновление инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    w
}
