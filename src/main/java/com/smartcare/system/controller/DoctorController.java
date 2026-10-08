package com.smartcare.system.controller;

import com.smartcare.system.entity.Doctor;
import com.smartcare.system.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // 1. Add Doctor (Create) - @Valid enabled
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
    }

    // 2. Search / Get All Doctors (Read All)
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    // Search Doctor by ID (Read Single)
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    // 3. Update Doctor Details - @Valid enabled
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Integer id, @Valid @RequestBody Doctor doctorDetails) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctorDetails));
    }

    // 4. Delete Doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor record deleted successfully");
    }

    // 5. Assign Doctor to Department
    @PutMapping("/{doctorId}/department/{departmentId}")
    public ResponseEntity<Doctor> assignToDepartment(
            @PathVariable Integer doctorId,
            @PathVariable Integer departmentId) {
        return ResponseEntity.ok(doctorService.assignDoctorToDepartment(doctorId, departmentId));
    }
}