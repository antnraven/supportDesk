package com.example.image.repository;

import com.example.image.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByInitiatorId(Long initiatorId);
}
