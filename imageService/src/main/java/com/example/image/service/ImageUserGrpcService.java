package com.example.image.service;

import com.example.grpc.generated.*;
import com.example.image.mapper.ImageMapper;
import com.example.image.repository.ImageRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class ImageUserGrpcService extends UserImageServiceGrpc.UserImageServiceImplBase {
    private final ImageRepository repository;
    private final ImageMapper imageMapper;

    public ImageUserGrpcService(@Autowired ImageRepository repository, @Autowired ImageMapper imageMapper) {
        this.repository = repository;
        this.imageMapper = imageMapper;
    }

    @Override
    public void getImagesById(GetImagesByIdRequest request, StreamObserver<GetImagesResponse> responseObserver) {
        List<com.example.grpc.generated.Image> imageList = imageMapper.toProtoList(repository.findByInitiatorId(request.getId()));

        GetImagesResponse response = GetImagesResponse.newBuilder()
                .addAllImages(imageList)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
