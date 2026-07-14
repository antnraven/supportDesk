package com.example.incident.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import com.example.incident.enums.IncidentStatus;
import com.example.incident.enums.Priority;
import com.example.incident.enums.ResponsibleService;
import lombok.ToString;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@ToString
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private OffsetDateTime dateCreate;
    private OffsetDateTime dateClosed;
    private Long analystId;
    private Long initiatorId;
    private IncidentStatus status;
    private Priority priority;
    private ResponsibleService responsibleService;
}
