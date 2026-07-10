package com.example.incident.dto;

import com.example.incident.enums.IncidentStatus;
import com.example.incident.enums.Priority;
import com.example.incident.enums.ResponsibleService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder(setterPrefix = "with", access = AccessLevel.PUBLIC)
@Getter
public class IncidentDto {
    private final long id;
    private final String name;
    private final String description;
    private final OffsetDateTime dateCreate;
    private final OffsetDateTime dateClosed;
    private final Long analystId;
    private final Long initiatorId;
    private final IncidentStatus status;
    private final Priority priority;
    private final ResponsibleService responsibleService;
}
