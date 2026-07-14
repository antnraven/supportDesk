package com.example.incident.mapper;

import com.example.incident.dto.IncidentDto;
import com.example.incident.entities.Incident;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IncidentMapper {
    IncidentMapper INSTANCE = Mappers.getMapper(IncidentMapper.class);
    Incident toSelf(IncidentDto incidentDto);
}
