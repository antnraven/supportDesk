package com.example.image.service.impl;

import com.example.image.entities.Image;
import com.example.image.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private RestClient restClient;

    @Mock
    private ObjectStorageClient objectStorageClient;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    void save_shouldThrowException_whenUrlIsUnsafe() {
        Image image = new Image();
        image.setUrl("https://evil.com/file.jpg");

        assertThrows(RuntimeException.class, () -> imageService.save(image));
        verify(imageRepository, never()).save(any());
    }

    @Test
    void findAll_shouldReturnPageOfImages() {
        Image image = new Image();
        Page<Image> page = new PageImpl<>(List.of(image));
        when(imageRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Image> result = imageService.findAll();

        assertEquals(1, result.getContent().size());
        verify(imageRepository).findAll(any(Pageable.class));
    }

    @Test
    void findAll_shouldThrowRuntimeException_onError() {
        when(imageRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> imageService.findAll());
    }

    @Test
    void findById_shouldReturnImage() {
        Image image = new Image();
        image.setId(1L);
        when(imageRepository.findById(1L)).thenReturn(java.util.Optional.of(image));

        Image result = imageService.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void findById_shouldThrowException_whenImageNotFound() {
        when(imageRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(RuntimeException.class, () -> imageService.findById(1L));
    }

    @Test
    void findById_shouldThrowRuntimeException_onError() {
        when(imageRepository.findById(1L)).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> imageService.findById(1L));
    }

    @Test
    void updateImage_shouldFindExistingAndThrowOnSave() {
        Image existing = new Image();
        existing.setId(1L);
        existing.setUrl("https://disk.yandex.ru/old.jpg");

        Image update = new Image();
        update.setId(1L);
        update.setUrl("https://disk.yandex.ru/new.jpg");

        when(imageRepository.findById(1L)).thenReturn(java.util.Optional.of(existing));

        assertThrows(RuntimeException.class, () -> imageService.updateImage(1L, update));
        verify(imageRepository).findById(1L);
    }

    @Test
    void updateImage_shouldThrowException_whenImageNotFound() {
        Image image = new Image();
        image.setId(1L);
        when(imageRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> imageService.updateImage(1L, image));
        verify(imageRepository, never()).save(any());
    }

    @Test
    void delete_shouldDeleteImageAndFile() {
        Image image = new Image();
        image.setId(1L);
        image.setFileName("test.jpg");
        when(imageRepository.findById(1L)).thenReturn(java.util.Optional.of(image));

        imageService.delete(1L);

        verify(objectStorageClient).deleteFile("test.jpg");
        verify(imageRepository).deleteById(1L);
    }

    @Test
    void delete_shouldThrowException_whenImageNotFound() {
        when(imageRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(RuntimeException.class, () -> imageService.delete(1L));
    }

    @Test
    void findAllByInitiatorId_shouldReturnImages() {
        Image image = new Image();
        when(imageRepository.findByInitiatorId(1L)).thenReturn(List.of(image));

        List<Image> result = imageService.findAllByInitiatorId(1L);

        assertEquals(1, result.size());
    }

    @Test
    void findAllByInitiatorId_shouldThrowRuntimeException_onError() {
        when(imageRepository.findByInitiatorId(1L)).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> imageService.findAllByInitiatorId(1L));
    }
}