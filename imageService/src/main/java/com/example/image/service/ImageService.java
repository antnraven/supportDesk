package com.example.image.service;

import com.example.image.entities.Image;
import com.example.image.repository.ImageRepository;
import com.example.image.utils.BeanUtils;
import com.example.image.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;

import static com.example.image.utils.FileUtils.isForbiddenExtension;

@Service
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;
    private final RestClient restClient;
    private final ObjectStorageClient objectStorageClient;

    public ImageService(@Autowired ImageRepository imageRepository, @Autowired RestClient restClient, @Autowired ObjectStorageClient objectStorageClient) {
        this.imageRepository = imageRepository;
        this.restClient = restClient;
        this.objectStorageClient = objectStorageClient;
    }

    public void save(Image image) {
        try {
            FileUtils.validateUrlSafety(image.getUrl());
            String originalFilename = FilenameUtils.getName(new URI(image.getUrl()).getPath());

            if (isForbiddenExtension(originalFilename)) {
                throw new IllegalArgumentException("Загрузка файлов данного типа запрещена.");
            }

            ResponseEntity<byte[]> response = restClient.get()
                    .uri(image.getUrl())
                    .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 SpringBootDownloader")
                    .retrieve()
                    .toEntity(byte[].class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Внешний сервер вернул ошибку: " + response.getStatusCode().value());
            }

            image.setUrl(saveImageAndGetUrl(image, response));
            imageRepository.save(image);
        } catch (Exception e) {
            log.error("Ошибка сохранения данных об изображении: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String saveImageAndGetUrl(Image image, ResponseEntity<byte[]> response) {
        if (response.getBody() != null) {
            try (InputStream is = new ByteArrayInputStream(response.getBody())) {
                return objectStorageClient.uploadFile(image.getFileName(), is, image.getSize(), image.getMediaType());
            } catch (Exception e) {
                log.error("Ошибка обработки изображения: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return "";
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

    public void updateImage(Long id, Image image) {
        try {
            var img = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Изображение " + image.getId() + " не найдено."));

            BeanUtils.setIfNotEqual(img.getId(), image.getId(), img::setId);
            BeanUtils.setIfNotEqual(img.getIncidentId(), image.getIncidentId(), img::setIncidentId);
            BeanUtils.setIfNotEqual(img.getUrl(), image.getUrl(), img::setUrl);
            BeanUtils.setIfNotEqual(img.getFileName(), image.getFileName(), img::setFileName);
            BeanUtils.setIfNotEqual(img.getSize(), image.getSize(), img::setSize);
            BeanUtils.setIfNotEqual(img.getMediaType(), image.getMediaType(), img::setMediaType);

            save(image);

        } catch (Exception e) {
            log.error("Ошибка обновления данных об изображении: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void delele(Long id) {
        try {
            var img = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Изображение " + id + " не найдено."));

            objectStorageClient.deleteFile(img.getFileName());
            imageRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
