package com.example.image.service.impl;

import com.example.grpc.generated.GetImagesByIdRequest;
import com.example.grpc.generated.GetImagesResponse;
import com.example.grpc.generated.Image;
import com.example.image.mapper.ImageMapper;
import com.example.image.repository.ImageRepository;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageUserGrpcServiceTest {

    @Mock
    private ImageRepository repository;

    @Mock
    private ImageMapper imageMapper;

    @InjectMocks
    private ImageUserGrpcService grpcService;

    @Captor
    private ArgumentCaptor<GetImagesResponse> responseCaptor;

    @Test
    void getImagesById_shouldReturnImages() {
        com.example.image.entities.Image internalImage = new com.example.image.entities.Image();
        internalImage.setId(1L);
        internalImage.setIncidentId(10L);

        Image protoImage = Image.newBuilder().setId(1L).setIncidentId(10L).build();

        when(repository.findByInitiatorId(10L)).thenReturn(List.of(internalImage));
        when(imageMapper.toProtoList(List.of(internalImage))).thenReturn(List.of(protoImage));

        StreamObserver<GetImagesResponse> observer = mock(StreamObserver.class);
        GetImagesByIdRequest request = GetImagesByIdRequest.newBuilder().setId(10L).build();

        grpcService.getImagesById(request, observer);

        verify(observer).onNext(responseCaptor.capture());
        verify(observer).onCompleted();
        assertEquals(1, responseCaptor.getValue().getImagesCount());
        assertEquals(1L, responseCaptor.getValue().getImages(0).getId());
    }
}