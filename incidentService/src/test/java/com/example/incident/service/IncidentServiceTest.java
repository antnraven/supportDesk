package com.example.incident.service;

import com.example.grpc.generated.*;
import com.example.incident.dto.IncidentDto;
import com.example.incident.dto.IncidentDetail;
import com.example.incident.entities.Incident;
import com.example.incident.enums.IncidentStatus;
import com.example.incident.enums.Priority;
import com.example.incident.enums.ResponsibleService;
import com.example.incident.mapper.ImageMapper;
import com.example.incident.mapper.UserMapper;
import com.example.incident.repository.IncidentRepository;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncidentServiceTest {

    @Mock
    private IncidentRepository incidentRepository;

    @Mock
    private KafkaTemplate<Long, IncidentDto> sender;

    @Mock
    private UserImageServiceGrpc.UserImageServiceBlockingStub blockingStub;

    @Mock
    private ImageMapper imageMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private IncidentService incidentService;

    @Captor
    private ArgumentCaptor<IncidentDto> dtoCaptor;

    @Test
    void save_shouldSaveIncidentAndSendToKafka() {
        Incident incident = new Incident();
        incident.setId(1L);
        incident.setName("Test");
        incident.setDescription("Desc");
        incident.setDateCreate(OffsetDateTime.now());
        incident.setAnalystId(100L);
        incident.setInitiatorId(200L);
        incident.setStatus(IncidentStatus.OPEN);
        incident.setPriority(Priority.MEDIUM);
        incident.setResponsibleService(ResponsibleService.HARDWARE_SERVICE);

        when(sender.send(anyString(), anyLong(), any(IncidentDto.class)))
                .thenReturn(CompletableFuture.completedFuture(null));

        incidentService.save(incident);

        verify(incidentRepository).save(incident);
        verify(sender).send(eq("incident-queue"), eq(1L), dtoCaptor.capture());
        assertEquals("Test", dtoCaptor.getValue().getName());
    }

    @Test
    void save_shouldThrowRuntimeException_onError() {
        Incident incident = new Incident();
        doThrow(new RuntimeException("DB error")).when(incidentRepository).save(any());

        assertThrows(RuntimeException.class, () -> incidentService.save(incident));
        verify(sender, never()).send(anyString(), anyLong(), any());
    }

    @Test
    void findAll_shouldReturnPageOfIncidents() {
        Incident incident = new Incident();
        Page<Incident> page = new PageImpl<>(List.of(incident));
        when(incidentRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Incident> result = incidentService.findAll();

        assertEquals(1, result.getContent().size());
        verify(incidentRepository).findAll(any(Pageable.class));
    }

    @Test
    void findAll_shouldThrowRuntimeException_onError() {
        when(incidentRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> incidentService.findAll());
    }

    @Test
    void findById_shouldReturnIncident() {
        Incident incident = new Incident();
        incident.setId(1L);
        when(incidentRepository.findById(1L)).thenReturn(Optional.of(incident));

        Incident result = incidentService.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void findById_shouldThrowException_whenIncidentNotFound() {
        when(incidentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> incidentService.findById(1L));
    }

    @Test
    void findById_shouldThrowRuntimeException_onError() {
        when(incidentRepository.findById(1L)).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> incidentService.findById(1L));
    }

    @Test
    void getIncidentDetail_shouldReturnDetail() {
        Incident incident = new Incident();
        incident.setId(1L);
        incident.setName("Test");
        incident.setDescription("Desc");
        incident.setDateCreate(OffsetDateTime.now());
        incident.setAnalystId(100L);
        incident.setInitiatorId(200L);
        incident.setStatus(IncidentStatus.OPEN);
        incident.setPriority(Priority.MEDIUM);
        incident.setResponsibleService(ResponsibleService.HARDWARE_SERVICE);

        com.example.grpc.generated.Image protoImage = com.example.grpc.generated.Image.newBuilder().setId(1L).build();
        com.example.grpc.generated.GetImagesResponse imagesResponse = GetImagesResponse.newBuilder()
                .addAllImages(List.of(protoImage))
                .build();

        com.example.grpc.generated.User protoUser = com.example.grpc.generated.User.newBuilder().setId(100L).setUserFullname("Analyst").build();
        com.example.grpc.generated.GetUserResponse analystResponse = GetUserResponse.newBuilder().setUser(protoUser).build();
        com.example.grpc.generated.GetUserResponse initiatorResponse = GetUserResponse.newBuilder().setUser(protoUser).build();

        com.example.incident.dto.Image dtoImage = com.example.incident.dto.Image.builder().id(1L).build();
        com.example.incident.dto.User dtoUser = com.example.incident.dto.User.builder().id(100L).userFullname("Analyst").build();

        when(incidentRepository.findById(1L)).thenReturn(Optional.of(incident));
        when(blockingStub.getImagesById(any(GetImagesByIdRequest.class))).thenReturn(imagesResponse);
        when(blockingStub.getUserByAnalystId(any(GetUserByAnalystIdRequest.class))).thenReturn(analystResponse);
        when(blockingStub.getUserByInitiatorId(any(GetUserByInitiatorIdRequest.class))).thenReturn(initiatorResponse);
        when(imageMapper.fromProtoList(anyList())).thenReturn(List.of(dtoImage));
        when(userMapper.fromProto(any(com.example.grpc.generated.User.class))).thenReturn(dtoUser);

        IncidentDetail result = incidentService.getIncidentDetail(1L);

        assertEquals(1L, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(1, result.getImageList().size());
        assertNotNull(result.getAnalyst());
        assertNotNull(result.getInitiator());
    }

    @Test
    void updateIncident_shouldUpdate_whenIncidentExists() {
        Incident incident = new Incident();
        incident.setId(1L);
        when(incidentRepository.existsById(1L)).thenReturn(true);

        incidentService.updateIncident(incident);

        verify(incidentRepository).save(incident);
    }

    @Test
    void updateIncident_shouldThrowException_whenIncidentNotFound() {
        Incident incident = new Incident();
        incident.setId(1L);
        when(incidentRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> incidentService.updateIncident(incident));
        verify(incidentRepository, never()).save(any());
    }

    @Test
    void updateIncident_shouldThrowRuntimeException_onError() {
        Incident incident = new Incident();
        incident.setId(1L);
        when(incidentRepository.existsById(1L)).thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> incidentService.updateIncident(incident));
    }
}