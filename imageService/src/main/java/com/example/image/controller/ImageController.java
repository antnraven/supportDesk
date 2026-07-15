package com.example.image.controller;

import com.example.image.dto.ImageDto;
import com.example.image.mapper.ImageMapper;
import com.example.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;
    private final ImageMapper imageMapper;

    public ImageController(@Autowired ImageService imageService, @Autowired ImageMapper imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST', 'INITIATOR')")
    public ResponseEntity<?> addIncident(@RequestBody ImageDto image) {
        try {
            imageService.save(imageMapper.toSelf(image));
            return ResponseEntity.ok("Успешное сохранение инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(imageService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(imageService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/initiatorId{initiatorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
    public ResponseEntity<?> findByInitiatorId(@PathVariable Long initiatorId) {
        try {
            return ResponseEntity.ok(imageService.findAllByInitiatorId(initiatorId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST', 'INITIATOR')")
    public ResponseEntity<?> updateIncident(@RequestParam Long id, @RequestBody ImageDto image) {
        try {
            imageService.updateImage(id, imageMapper.toSelf(image));
            return ResponseEntity.ok("Успешное обновление инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/id{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            imageService.delete(id);
            return ResponseEntity.ok("Успешное удаление изображения " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
