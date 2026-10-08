package com.smartcare.system.controller;

import com.smartcare.system.entity.LabTest;
import com.smartcare.system.service.LabTestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lab-tests")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    // 1. Add Laboratory Test (POST) - @Valid enabled
    @PostMapping
    public ResponseEntity<?> addLabTest(@Valid @RequestBody LabTest labTest) {
        try {
            return ResponseEntity.ok(labTestService.addLabTest(labTest));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 2. Update Laboratory Result (PUT)
    @PutMapping("/{id}/result")
    public ResponseEntity<?> updateLabResult(
            @PathVariable Integer id,
            @RequestBody Map<String, String> payload) {
        try {
            String result = payload.get("testResult");
            String technician = payload.get("technicianName");
            String status = payload.get("status");

            LabTest updated = labTestService.updateLabResult(id, result, technician, status);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 3. View Patient Laboratory History (GET)
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<LabTest>> getPatientLabHistory(@PathVariable Integer patientId) {
        return ResponseEntity.ok(labTestService.getPatientLabHistory(patientId));
    }

    // 4. Get All Lab Tests (GET)
    @GetMapping
    public ResponseEntity<List<LabTest>> getAllLabTests() {
        return ResponseEntity.ok(labTestService.getAllLabTests());
    }
}