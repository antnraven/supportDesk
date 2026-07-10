package com.example.incident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.incident.entities.Incident;
import com.example.incident.service.IncidentService;

@RestController
@RequestMapping("/incident")
public class IncidentController {
    private final IncidentService incidentService;

    public IncidentController(@Autowired IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public ResponseEntity<?> addIncident(@RequestBody Incident incident) {
        try {
            incidentService.save(incident);
            return ResponseEntity.ok("Успешное сохранение инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(incidentService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incidentService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PatchMapping
    public ResponseEntity<?> updateIncident(@RequestBody Incident incident) {
        try {
            incidentService.updateIncident(incident);
            return ResponseEntity.ok("Успешное обновление инцидента");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
