package com.smartcare.system.service;

import com.smartcare.system.entity.Patient;
import com.smartcare.system.exception.ResourceNotFoundException;
import com.smartcare.system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientByID(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Integer id, Patient updatedPatient) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));

        patient.setFullName(updatedPatient.getFullName());
        patient.setDob(updatedPatient.getDob());
        patient.setGender(updatedPatient.getGender());
        patient.setAddress(updatedPatient.getAddress());
        patient.setContactNumber(updatedPatient.getContactNumber());
        patient.setBloodGroup(updatedPatient.getBloodGroup());
        patient.setEmergencyContact(updatedPatient.getEmergencyContact());

        return patientRepository.save(patient);
    }

    public boolean deletePatient(Integer id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with ID: " + id);
        }
        patientRepository.deleteById(id);
        return true;
    }
}