package com.example.incident.repository;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.incident.entities.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Page<Incident> findAll(DataWebProperties.Pageable pageable);
}
