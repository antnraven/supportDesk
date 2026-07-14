package com.example.image.service.impl;

import io.minio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ObjectStorageClientTest {

    @Mock
    private MinioClient minioClient;

    private ObjectStorageClient objectStorageClient;

    @BeforeEach
    void setUp() {
        objectStorageClient = new ObjectStorageClient(minioClient);
        ReflectionTestUtils.setField(objectStorageClient, "bucketName", "test-bucket");
    }

    @Test
    void uploadFile_shouldCreateBucketAndUpload_whenBucketDoesNotExist() throws Exception {
        when(minioClient.bucketExists(any(BucketExistsArgs.class))).thenReturn(false);
        when(minioClient.getPresignedObjectUrl(any(GetPresignedObjectUrlArgs.class)))
                .thenThrow(new RuntimeException("MinIO error"));

        InputStream inputStream = new ByteArrayInputStream("test".getBytes());
        assertThrows(RuntimeException.class, () ->
                objectStorageClient.uploadFile("test.jpg", inputStream, 4L, "image/jpeg")
        );

        verify(minioClient).makeBucket(any(MakeBucketArgs.class));
    }

    @Test
    void uploadFile_shouldUploadWithoutCreatingBucket_whenBucketExists() throws Exception {
        when(minioClient.bucketExists(any(BucketExistsArgs.class))).thenReturn(true);
        when(minioClient.getPresignedObjectUrl(any(GetPresignedObjectUrlArgs.class)))
                .thenThrow(new RuntimeException("MinIO error"));

        InputStream inputStream = new ByteArrayInputStream("test".getBytes());
        assertThrows(RuntimeException.class, () ->
                objectStorageClient.uploadFile("test.jpg", inputStream, 4L, "image/jpeg")
        );

        verify(minioClient, never()).makeBucket(any(MakeBucketArgs.class));
    }

    @Test
    void uploadFile_shouldThrowRuntimeException_onMinioError() throws Exception {
        when(minioClient.bucketExists(any(BucketExistsArgs.class))).thenThrow(new RuntimeException("MinIO error"));

        InputStream inputStream = new ByteArrayInputStream("test".getBytes());
        assertThrows(RuntimeException.class, () ->
                objectStorageClient.uploadFile("test.jpg", inputStream, 4L, "image/jpeg")
        );
    }

    @Test
    void getFile_shouldReturnInputStream() throws Exception {
        GetObjectResponse expected = mock(GetObjectResponse.class);
        when(minioClient.getObject(any(GetObjectArgs.class))).thenReturn(expected);

        InputStream result = objectStorageClient.getFile("test.jpg");

        assertEquals(expected, result);
    }

    @Test
    void getFile_shouldThrowRuntimeException_onError() throws Exception {
        when(minioClient.getObject(any(GetObjectArgs.class))).thenThrow(new RuntimeException("MinIO error"));

        assertThrows(RuntimeException.class, () -> objectStorageClient.getFile("test.jpg"));
    }

    @Test
    void deleteFile_shouldCallRemoveObject() throws Exception {
        objectStorageClient.deleteFile("test.jpg");
        verify(minioClient).removeObject(any(RemoveObjectArgs.class));
    }

    @Test
    void deleteFile_shouldThrowRuntimeException_onError() throws Exception {
        doThrow(new RuntimeException("MinIO error")).when(minioClient).removeObject(any(RemoveObjectArgs.class));

        assertThrows(RuntimeException.class, () -> objectStorageClient.deleteFile("test.jpg"));
    }
}