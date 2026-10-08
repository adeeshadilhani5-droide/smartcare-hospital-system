package com.smartcare.system.service;

import com.smartcare.system.entity.Admission;
import com.smartcare.system.entity.Patient;
import com.smartcare.system.entity.Room;
import com.smartcare.system.repository.AdmissionRepository;
import com.smartcare.system.repository.PatientRepository;
import com.smartcare.system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PatientRepository patientRepository;

    // 1. Admit Patient & Allocate Room (Fix 6: Prevent Allocating Unavailable Rooms)
    @Transactional
    public Admission admitPatient(Admission admission) {
        // Patient සොයාගැනීම
        Patient patient = patientRepository.findById(admission.getPatient().getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + admission.getPatient().getPatientId()));

        // Room සොයාගැනීම සහ Availability පරීක්ෂාව
        Room room = roomRepository.findById(admission.getRoom().getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + admission.getRoom().getRoomId()));

        if (!Boolean.TRUE.equals(room.getIsAvailable())) {
            throw new RuntimeException("Selected room (Room ID: " + room.getRoomId() + ") is currently occupied or unavailable!");
        }

        // Room එක Book කිරීම (isAvailable = false)
        room.setIsAvailable(false);
        roomRepository.save(room);

        // Admission Object එක සම්පූර්ණ කිරීම
        admission.setPatient(patient);
        admission.setRoom(room);
        if (admission.getAdmissionDate() == null) {
            admission.setAdmissionDate(LocalDate.now());
        }

        return admissionRepository.save(admission);
    }

    // 2. Discharge Patient & Free Room
    @Transactional
    public Admission dischargePatient(Integer admissionId) {
        Admission admission = admissionRepository.findById(admissionId)
                .orElseThrow(() -> new RuntimeException("Admission record not found with ID: " + admissionId));

        if (admission.getDischargeDate() != null) {
            throw new RuntimeException("Patient is already discharged!");
        }

        admission.setDischargeDate(LocalDate.now());

        // Room එක නැවත Free කිරීම (isAvailable = true)
        Room room = admission.getRoom();
        if (room != null) {
            room.setIsAvailable(true);
            roomRepository.save(room);
        }

        return admissionRepository.save(admission);
    }

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }
}