package com.smartcare.system.controller;

import com.smartcare.system.entity.Treatment;
import com.smartcare.system.service.TreatmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    // Record Diagnosis & Prescription (POST)
    @PostMapping
    public ResponseEntity<?> recordTreatment(@Valid @RequestBody Treatment treatment) {
        try {
            Treatment savedTreatment = treatmentService.recordTreatment(treatment);
            return ResponseEntity.ok(savedTreatment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // View Patient Medical History (GET)
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Treatment>> getPatientMedicalHistory(@PathVariable Integer patientId) {
        return ResponseEntity.ok(treatmentService.getPatientMedicalHistory(patientId));
    }

    // Get All Treatments (GET)
    @GetMapping
    public ResponseEntity<List<Treatment>> getAllTreatments() {
        return ResponseEntity.ok(treatmentService.getAllTreatments());
    }

    // Get Treatment by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> getTreatmentById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(treatmentService.getTreatmentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}