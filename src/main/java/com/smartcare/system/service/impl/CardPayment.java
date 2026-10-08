package com.smartcare.system.service.impl;

import com.smartcare.system.service.PaymentService;
import org.springframework.stereotype.Service;

@Service("cardPayment")
public class CardPayment implements PaymentService {

    @Override
    public boolean processPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        System.out.println("Processing Card Payment of Rs: " + amount);
        return true;
    }

    @Override
    public String getPaymentType() {
        return "CARD";
    }
}