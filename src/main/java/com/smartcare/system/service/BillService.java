package com.smartcare.system.service;

import com.smartcare.system.entity.Bill;
import com.smartcare.system.entity.Patient;
import com.smartcare.system.repository.BillRepository;
import com.smartcare.system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PatientRepository patientRepository;

    // 1. Generate Bill
    public Bill generateBill(Bill bill) {
        // Patient ID එක DB හි තිබේදැයි පරීක්ෂා කර Full Patient Data ඇතුළත් කිරීම
        if (bill.getPatient() != null && bill.getPatient().getPatientId() != null) {
            Patient patient = patientRepository.findById(bill.getPatient().getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + bill.getPatient().getPatientId()));
            bill.setPatient(patient);
        }

        // Total Amount ගණනය කිරීම
        double total = 0.0;
        if (bill.getDoctorCharge() != null) total += bill.getDoctorCharge();
        if (bill.getRoomCharge() != null) total += bill.getRoomCharge();
        if (bill.getLabCharge() != null) total += bill.getLabCharge();
        if (bill.getMedicineCharge() != null) total += bill.getMedicineCharge();

        bill.setTotalAmount(total);

        if (bill.getBillDate() == null) {
            bill.setBillDate(LocalDate.now());
        }
        if (bill.getPaymentStatus() == null) {
            bill.setPaymentStatus("PENDING");
        }

        return billRepository.save(bill);
    }

    // 2. Process Payment
    public Bill processPayment(Integer billId, String paymentMethod) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found with ID: " + billId));

        if ("PAID".equalsIgnoreCase(bill.getPaymentStatus())) {
            throw new RuntimeException("This bill has already been paid!");
        }

        bill.setPaymentStatus("PAID");
        bill.setPaymentMethod(paymentMethod != null ? paymentMethod : "CASH");

        return billRepository.save(bill);
    }

    // 3. Get Patient Bills
    public List<Bill> getBillsByPatientId(Integer patientId) {
        return billRepository.findByPatientPatientIdOrderByBillDateDesc(patientId);
    }

    // 4. Get Bill By ID
    public Bill getBillById(Integer id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill record not found with ID: " + id));
    }

    // 5. Get All Bills
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
}