package com.smartcare.system.controller;

import com.smartcare.system.entity.Bill;
import com.smartcare.system.service.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    // Generate Bill (POST) - @Valid enabled
    @PostMapping
    public ResponseEntity<?> createBill(@Valid @RequestBody Bill bill) {
        try {
            Bill createdBill = billService.generateBill(bill);
            return ResponseEntity.ok(createdBill);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Process Payment (PUT)
    @PutMapping("/{id}/pay")
    public ResponseEntity<?> processPayment(
            @PathVariable Integer id,
            @RequestBody Map<String, String> payload) {
        try {
            String paymentMethod = payload.get("paymentMethod");
            Bill paidBill = billService.processPayment(id, paymentMethod);
            return ResponseEntity.ok(paidBill);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // View Patient Bills (GET)
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Bill>> getBillsByPatient(@PathVariable Integer patientId) {
        return ResponseEntity.ok(billService.getBillsByPatientId(patientId));
    }

    // Get Bill by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> getBillById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(billService.getBillById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get All Bills (GET)
    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }
}