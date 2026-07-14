package com.example.incident.service;

import com.example.grpc.generated.*;
import com.example.incident.dto.Image;
import com.example.incident.dto.IncidentDetail;
import com.example.incident.dto.IncidentDto;
import com.example.incident.mapper.ImageMapper;
import com.example.incident.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.incident.entities.Incident;
import com.example.incident.repository.IncidentRepository;

import java.util.List;


@Service
@Slf4j
public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final KafkaTemplate<Long, IncidentDto> sender;
    private final UserImageServiceGrpc.UserImageServiceBlockingStub blockingStub;
    private final ImageMapper imageMapper;
    private final UserMapper userMapper;

    public IncidentService(@Autowired IncidentRepository incidentRepository, @Autowired KafkaTemplate<Long, IncidentDto> sender, @GrpcClient("user-image-service") UserImageServiceGrpc.UserImageServiceBlockingStub blockingStub, @Autowired ImageMapper imageMapper, @Autowired UserMapper userMapper) {
        this.incidentRepository = incidentRepository;
        this.sender = sender;
        this.blockingStub = blockingStub;
        this.imageMapper = imageMapper;
        this.userMapper = userMapper;
    }

    public void save(Incident incident) {
        try {
            incidentRepository.save(incident);

            IncidentDto incidentDto = IncidentDto.builder()
                    .id(incident.getId())
                    .name(incident.getName())
                    .description(incident.getDescription())
                    .dateCreate(incident.getDateCreate())
                    .dateClosed(incident.getDateClosed())
                    .analystId(incident.getAnalystId())
                    .initiatorId(incident.getInitiatorId())
                    .status(incident.getStatus())
                    .priority(incident.getPriority())
                    .responsibleService(incident.getResponsibleService())
                    .build();

            sender.send("incident-queue", incidentDto.getId(), incidentDto)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.error("Ошибка отправки");
                        } else {
                            log.info("Успешно отправлено в партицию {}", result.getRecordMetadata().partition());
                        }
                    });
        } catch (Exception e) {
            log.error("Ошибка сохранения данных об инциденте: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Page<Incident> findAll() {
        try {
            Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
            return incidentRepository.findAll(pageable);
        } catch (Exception e) {
            log.error("Ошибка получения данных обо всех инцидентах: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Incident findById(Long id) {
        try {
            return incidentRepository.findById(id).orElseThrow(() -> new RuntimeException("Инцидент " + id + " не найден"));
        } catch (Exception e) {
            log.error("Ошибка получения данных: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public IncidentDetail getIncidentDetail(Long id) {
        var incident = findById(id);
        return IncidentDetail.builder()
                .id(incident.getId())
                .dateClosed(incident.getDateClosed())
                .dateCreate(incident.getDateClosed())
                .description(incident.getDescription())
                .name(incident.getName())
                .priority(incident.getPriority())
                .responsibleService(incident.getResponsibleService())
                .imageList(getImageList(id))
                .analyst(getAnalystById(incident.getAnalystId()))
                .initiator(getInitiatorById(incident.getInitiatorId()))
                .build();
    }

    private List<Image> getImageList(Long id) {
        return imageMapper.fromProtoList(getImagesById(id).getImagesList());
    }

    private com.example.incident.dto.User getAnalystById(Long analystId) {
        return userMapper.fromProto(getUserByAnalystId(analystId).getUser());
    }

    private com.example.incident.dto.User getInitiatorById(Long analystId) {
        return userMapper.fromProto(getUserByInitiatorId(analystId).getUser());
    }

    public void updateIncident(Incident incident) {
        try {
            if (incidentRepository.existsById(incident.getId())) {
                incidentRepository.save(incident);
            } else {
                throw new RuntimeException("Инцидент " + incident.getId() + " не найден.");
            }
        } catch (Exception e) {
            log.error("Ошибка обновления данных об инциденте: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private GetImagesResponse getImagesById(Long id) {
        GetImagesByIdRequest request = GetImagesByIdRequest.newBuilder().setId(id).build();
        return blockingStub.getImagesById(request);
    }

    private GetUserResponse getUserByAnalystId(Long analystId) {
        GetUserByAnalystIdRequest request = GetUserByAnalystIdRequest.newBuilder()
                .setAnalystId(analystId)
                .build();
        return blockingStub.getUserByAnalystId(request);
    }

    private GetUserResponse getUserByInitiatorId(Long initiatorId) {
        GetUserByInitiatorIdRequest request = GetUserByInitiatorIdRequest.newBuilder()
                .setInitiatorId(initiatorId)
                .build();
        return blockingStub.getUserByInitiatorId(request);
    }
}
