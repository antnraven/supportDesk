package com.example.incident.controller;

import com.example.incident.dto.IncidentDto;
import com.example.incident.mapper.IncidentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.incident.service.IncidentService;

@RestController
@RequestMapping("/incident")
public class IncidentController {
    private final IncidentService incidentService;
    private final IncidentMapper incidentMapper;

    public IncidentController(@Autowired IncidentService incidentService, @Autowired IncidentMapper incidentMapper) {
        this.incidentService = incidentService;
        this.incidentMapper = incidentMapper;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST', 'INITIATOR')")
    public ResponseEntity<?> addIncident(@RequestBody IncidentDto incident) {
        try {
            incidentService.save(incidentMapper.toSelf(incident));
            return ResponseEntity.ok("Успешное сохранение инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(incidentService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incidentService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detail{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
    public ResponseEntity<?> findDetailById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incidentService.getIncidentDetail(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PatchMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST', 'INITIATOR')")
    public ResponseEntity<?> updateIncident(@RequestBody IncidentDto incident) {
        try {
            incidentService.updateIncident(incidentMapper.toSelf(incident));
            return ResponseEntity.ok("Успешное обновление инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
