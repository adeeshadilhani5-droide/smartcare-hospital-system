package com.smartcare.system.controller;

import com.smartcare.system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    // Spring Boot automatically maps all PaymentService beans by their bean names
    private final Map<String, PaymentService> paymentServices;

    @Autowired
    public PaymentController(Map<String, PaymentService> paymentServices) {
        this.paymentServices = paymentServices;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(
            @RequestParam String type,
            @RequestParam double amount) {

        // Fixed bean mapping to append 'PaymentService' (e.g., cash -> cashPaymentService)
        String beanName = type.toLowerCase() + "PaymentService";
        PaymentService paymentService = paymentServices.get(beanName);

        if (paymentService == null) {
            return ResponseEntity.badRequest().body("Invalid payment type! Select cash, card, or online.");
        }

        try {
            boolean result = paymentService.processPayment(amount);
            if (result) {
                return ResponseEntity.ok(paymentService.getPaymentType() + " payment of Rs. " + amount + " processed successfully!");
            } else {
                return ResponseEntity.internalServerError().body("Payment failed!");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Payment error: " + e.getMessage());
        }
    }
}