package com.smartcare.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "BILL")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billId")
    private Integer billId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "AppointmentID")
    private Integer appointmentId;

    @Column(name = "AdmissionID")
    private Integer admissionId;

    @Column(name = "billDate")
    private LocalDate billDate;

    @Min(value = 0, message = "Doctor charge cannot be negative")
    @Column(name = "DoctorCharge")
    private Double doctorCharge;

    @Min(value = 0, message = "Room charge cannot be negative")
    @Column(name = "RoomCharge")
    private Double roomCharge;

    @Min(value = 0, message = "Lab charge cannot be negative")
    @Column(name = "LabCharge")
    private Double labCharge;

    @Min(value = 0, message = "Medicine charge cannot be negative")
    @Column(name = "MedicineCharge")
    private Double medicineCharge;

    @Min(value = 0, message = "Total amount cannot be negative")
    @Column(name = "totalAmount")
    private Double totalAmount;

    @Column(name = "paymentStatus")
    private String paymentStatus;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    public Bill() {}

    public Integer getBillId() { return billId; }
    public void setBillId(Integer billId) { this.billId = billId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Integer getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Integer appointmentId) { this.appointmentId = appointmentId; }

    public Integer getAdmissionId() { return admissionId; }
    public void setAdmissionId(Integer admissionId) { this.admissionId = admissionId; }

    public LocalDate getBillDate() { return billDate; }
    public void setBillDate(LocalDate billDate) { this.billDate = billDate; }

    public Double getDoctorCharge() { return doctorCharge; }
    public void setDoctorCharge(Double doctorCharge) { this.doctorCharge = doctorCharge; }

    public Double getRoomCharge() { return roomCharge; }
    public void setRoomCharge(Double roomCharge) { this.roomCharge = roomCharge; }

    public Double getLabCharge() { return labCharge; }
    public void setLabCharge(Double labCharge) { this.labCharge = labCharge; }

    public Double getMedicineCharge() { return medicineCharge; }
    public void setMedicineCharge(Double medicineCharge) { this.medicineCharge = medicineCharge; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}