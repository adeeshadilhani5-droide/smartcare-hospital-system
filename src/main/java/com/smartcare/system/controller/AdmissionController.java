package com.smartcare.system.controller;

import com.smartcare.system.entity.Admission;
import com.smartcare.system.service.AdmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    // 1. Admit Patient (POST)
    @PostMapping
    public ResponseEntity<?> admitPatient(@Valid @RequestBody Admission admission) {
        try {
            Admission savedAdmission = admissionService.admitPatient(admission);
            return ResponseEntity.ok(savedAdmission);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 2. Discharge Patient (PUT)
    @PutMapping("/{id}/discharge")
    public ResponseEntity<?> dischargePatient(@PathVariable Integer id) {
        try {
            Admission dischargedAdmission = admissionService.dischargePatient(id);
            return ResponseEntity.ok(dischargedAdmission);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 3. Get All Admissions (GET)
    @GetMapping
    public List<Admission> getAllAdmissions() {
        return admissionService.getAllAdmissions();
    }
}