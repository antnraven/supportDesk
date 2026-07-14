package com.example.image.service;

import com.example.image.entities.Image;

import org.springframework.data.domain.Page;

import java.util.List;


public interface ImageService {

    void save(Image image);

    Page<Image> findAll();

    Image findById(Long id);

    void updateImage(Long id, Image image);

    void delete(Long id);

    List<Image> findAllByInitiatorId(Long initiatorId);
}
