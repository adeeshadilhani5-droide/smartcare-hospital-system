package com.smartcare.system.service;

public interface PaymentService {
    boolean processPayment(double amount);
    String getPaymentType();
}