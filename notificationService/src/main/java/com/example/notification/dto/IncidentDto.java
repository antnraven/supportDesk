package com.example.notification.dto;

import com.example.notification.enums.IncidentStatus;
import com.example.notification.enums.Priority;
import com.example.notification.enums.ResponsibleService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Builder(setterPrefix = "with", access = AccessLevel.PUBLIC)
@Getter
@ToString
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
