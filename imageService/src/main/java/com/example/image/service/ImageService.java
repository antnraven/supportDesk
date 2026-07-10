package com.example.image.service;

import com.example.image.entities.Image;
import com.example.image.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(@Autowired ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(Image image) {
        try {
            imageRepository.save(image);
        } catch (Exception e) {
            log.error("Ошибка сохранения данных об изображении: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Page<Image> findAll() {
        try {
            Pageable pageable = PageRequest.of(0, 10, Sort.by("fileName"));
            return imageRepository.findAll(pageable);
        } catch (Exception e) {
            log.error("Ошибка получения данных обо всех изображениях: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Image findById(Long id) {
        try {
            return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Изображение" + id + " не найдено"));
        } catch (Exception e) {
            log.error("Ошибка получения данных: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateIncident(Image image) {
        try {
            if (imageRepository.existsById(image.getId())) {
                imageRepository.save(image);
            } else {
                throw new RuntimeException("Изображение " + image.getId() + " не найдено.");
            }
        } catch (Exception e) {
            log.error("Ошибка обновления данных об изображении: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
