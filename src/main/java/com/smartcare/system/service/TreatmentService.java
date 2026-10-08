package com.smartcare.system.service;

import com.smartcare.system.entity.Doctor;
import com.smartcare.system.entity.Patient;
import com.smartcare.system.entity.Treatment;
import com.smartcare.system.repository.DoctorRepository;
import com.smartcare.system.repository.PatientRepository;
import com.smartcare.system.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    // 1. Record Diagnosis & Prescription
    public Treatment recordTreatment(Treatment treatment) {
        // Patient සහ Doctor DB හි සිටීදැයි පරීක්ෂා කිරීම (Null Details පැමිණීම වැළැක්වීමට)
        Patient patient = patientRepository.findById(treatment.getPatient().getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + treatment.getPatient().getPatientId()));

        Doctor doctor = doctorRepository.findById(treatment.getDoctor().getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + treatment.getDoctor().getDoctorId()));

        treatment.setPatient(patient);
        treatment.setDoctor(doctor);

        if (treatment.getTreatmentDate() == null) {
            treatment.setTreatmentDate(LocalDate.now());
        }

        return treatmentRepository.save(treatment);
    }

    // 2. Get Medical History of a Patient
    public List<Treatment> getPatientMedicalHistory(Integer patientId) {
        return treatmentRepository.findByPatientPatientIdOrderByTreatmentDateDesc(patientId);
    }

    // 3. Get All Treatments
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    // 4. Get Treatment by ID
    public Treatment getTreatmentById(Integer id) {
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treatment record not found with ID: " + id));
    }
}