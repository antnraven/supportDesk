package com.example.user.service.impl;

import com.example.grpc.generated.*;
import com.example.grpc.generated.UserImageServiceGrpc;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@GrpcService
public class UserGrpcService extends UserImageServiceGrpc.UserImageServiceImplBase {

    private final UserService service;
    private final UserMapper mapper;

    public UserGrpcService(@Autowired UserService userService, @Autowired UserMapper userMapper) {
        this.service = userService;
        this.mapper = userMapper;
    }

    @Override
    public void getUserByAnalystId(GetUserByAnalystIdRequest request, StreamObserver<GetUserResponse> responseObserver) {
        CompletableFuture<com.example.user.entities.User> future = service.findById(request.getAnalystId());
        User user = null;
        try {
            user = mapper.toProto(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        GetUserResponse response = GetUserResponse.newBuilder().setUser(user).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUserByInitiatorId(GetUserByInitiatorIdRequest request, StreamObserver<GetUserResponse> responseObserver) {
        CompletableFuture<com.example.user.entities.User> future = service.findById(request.getInitiatorId());
        User user = null;
        try {
            user = mapper.toProto(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        GetUserResponse response = GetUserResponse.newBuilder().setUser(user).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
