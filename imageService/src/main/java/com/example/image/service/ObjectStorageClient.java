package com.example.image.service;

import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ObjectStorageClient {
    private final MinioClient minioClient;
    private final static int EXPIRES_IN_SECONDS = 60 * 60 * 24 * 7; //Время хранения ссылки 7 суток

    @Value("${minio.bucketName}")
    private String bucketName;

    public ObjectStorageClient(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String uploadFile(String objectName, InputStream inputStream, long size, String contentType) {
        boolean found = false;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, size, -1)
                            .contentType(contentType)
                            .build()
            );
            return generatePresignedUrl(objectName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream getFile(String objectName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFile(String objectName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generatePresignedUrl(String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(ObjectStorageClient.EXPIRES_IN_SECONDS)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка генерации ссылки", e);
        }
    }
}
