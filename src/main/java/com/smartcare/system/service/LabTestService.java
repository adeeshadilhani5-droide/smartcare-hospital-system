package com.smartcare.system.service;

import com.smartcare.system.entity.LabTest;
import com.smartcare.system.entity.Patient;
import com.smartcare.system.repository.LabTestRepository;
import com.smartcare.system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private PatientRepository patientRepository;

    // 1. Add Laboratory Test
    public LabTest addLabTest(LabTest labTest) {
        Patient patient = patientRepository.findById(labTest.getPatient().getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + labTest.getPatient().getPatientId()));

        labTest.setPatient(patient);

        if (labTest.getTestDate() == null) {
            labTest.setTestDate(LocalDate.now());
        }
        if (labTest.getStatus() == null) {
            labTest.setStatus("PENDING");
        }

        return labTestRepository.save(labTest);
    }

    // 2. Update Laboratory Result
    public LabTest updateLabResult(Integer labTestId, String result, String technicianName, String status) {
        LabTest labTest = labTestRepository.findById(labTestId)
                .orElseThrow(() -> new RuntimeException("Lab test record not found with ID: " + labTestId));

        if (result != null) {
            labTest.setTestResult(result);
        }
        if (technicianName != null) {
            labTest.setTechnicianName(technicianName);
        }
        if (status != null) {
            labTest.setStatus(status);
        } else {
            labTest.setStatus("COMPLETED");
        }

        return labTestRepository.save(labTest);
    }

    // 3. View Laboratory History for a Patient
    public List<LabTest> getPatientLabHistory(Integer patientId) {
        return labTestRepository.findByPatientPatientIdOrderByTestDateDesc(patientId);
    }

    // 4. Get All Lab Tests
    public List<LabTest> getAllLabTests() {
        return labTestRepository.findAll();
    }
}