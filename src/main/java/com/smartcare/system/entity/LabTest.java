package com.smartcare.system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lab_test")
public class LabTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer labTestId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String testName;
    private LocalDate testDate;
    private String testResult;
    private String technicianName;
    private String status;

    public LabTest() {}

    //geters and setters

    public Integer getLabTestId() {
        return labTestId;
    }

    public void setLabTestId(Integer labTestId) {
        this.labTestId = labTestId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}