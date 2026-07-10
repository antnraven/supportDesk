package com.example.incident.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.incident.entities.Incident;
import com.example.incident.repository.IncidentRepository;


@Service
@Slf4j
public class IncidentService {
    private final IncidentRepository incidentRepository;

    public IncidentService(@Autowired IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public void save(Incident incident) {
        try {
            incidentRepository.save(incident);
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
}
