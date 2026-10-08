package com.smartcare.system.service.impl;

import com.smartcare.system.service.PaymentService;
import org.springframework.stereotype.Service;

@Service("onlinePayment")
public class OnlinePayment implements PaymentService {

    @Override
    public boolean processPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        System.out.println("Processing Online Payment Gateway transfer of Rs: " + amount);
        return true;
    }

    @Override
    public String getPaymentType() {
        return "ONLINE";
    }
}