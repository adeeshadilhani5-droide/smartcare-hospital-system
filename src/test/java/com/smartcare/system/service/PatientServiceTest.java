package com.smartcare.system.service;

import com.smartcare.system.entity.Patient;
import com.smartcare.system.exception.ResourceNotFoundException;
import com.smartcare.system.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void testGetPatientById_Success() {
        Patient mockPatient = new Patient();
        mockPatient.setPatientId(1);
        mockPatient.setFullName("Nimal Perera");

        when(patientRepository.findById(1)).thenReturn(Optional.of(mockPatient));

        Patient foundPatient = patientService.getPatientByID(1);

        assertNotNull(foundPatient);
        assertEquals("Nimal Perera", foundPatient.getFullName());
        verify(patientRepository, times(1)).findById(1);
    }

    @Test
    void testGetPatientById_NotFound() {
        when(patientRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            patientService.getPatientByID(999);
        });
    }
}