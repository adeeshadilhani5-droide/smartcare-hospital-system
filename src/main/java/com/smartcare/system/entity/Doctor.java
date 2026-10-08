package com.smartcare.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "DOCTOR")
public class Doctor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoctorID")
    private Integer doctorId;

    @Column(name = "Qualification")
    private String qualification;

    @Column(name = "Specialization")
    private String specialization;

    @NotNull(message = "Consultation fee is required")
    @DecimalMin(value = "0.01", message = "Consultation fee must be greater than zero")
    @Column(name = "ConsultationFee")
    private Double consultationFee;

    @Column(name = "LicenseNumber")
    private String licenseNumber;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;

    public Doctor() {
        super();
    }

    // Getters and Setters
    public Integer getDoctorId() { return doctorId; }
    public void setDoctorId(Integer doctorId) { this.doctorId = doctorId; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}