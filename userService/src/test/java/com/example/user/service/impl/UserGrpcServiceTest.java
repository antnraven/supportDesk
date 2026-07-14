package com.example.user.service.impl;

import com.example.grpc.generated.GetUserByAnalystIdRequest;
import com.example.grpc.generated.GetUserByInitiatorIdRequest;
import com.example.grpc.generated.GetUserResponse;
import com.example.grpc.generated.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGrpcServiceTest {

    @Mock
    private UserService service;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserGrpcService grpcService;

    @Captor
    private ArgumentCaptor<GetUserResponse> responseCaptor;

    @Test
    void getUserByAnalystId_shouldReturnUser() {
        com.example.user.entities.User internalUser = new com.example.user.entities.User();
        internalUser.setId(1L);
        User protoUser = User.newBuilder().setId(1L).build();

        when(service.findById(1L)).thenReturn(CompletableFuture.completedFuture(internalUser));
        when(mapper.toProto(internalUser)).thenReturn(protoUser);

        StreamObserver<GetUserResponse> observer = mock(StreamObserver.class);
        GetUserByAnalystIdRequest request = GetUserByAnalystIdRequest.newBuilder().setAnalystId(1L).build();

        grpcService.getUserByAnalystId(request, observer);

        verify(observer).onNext(responseCaptor.capture());
        verify(observer).onCompleted();
        assertEquals(1L, responseCaptor.getValue().getUser().getId());
    }

    @Test
    void getUserByInitiatorId_shouldReturnUser() {
        com.example.user.entities.User internalUser = new com.example.user.entities.User();
        internalUser.setId(2L);
        User protoUser = User.newBuilder().setId(2L).build();

        when(service.findById(2L)).thenReturn(CompletableFuture.completedFuture(internalUser));
        when(mapper.toProto(internalUser)).thenReturn(protoUser);

        StreamObserver<GetUserResponse> observer = mock(StreamObserver.class);
        GetUserByInitiatorIdRequest request = GetUserByInitiatorIdRequest.newBuilder().setInitiatorId(2L).build();

        grpcService.getUserByInitiatorId(request, observer);

        verify(observer).onNext(responseCaptor.capture());
        verify(observer).onCompleted();
        assertEquals(2L, responseCaptor.getValue().getUser().getId());
    }
}