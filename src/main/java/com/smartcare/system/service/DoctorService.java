package com.smartcare.system.service;

import com.smartcare.system.entity.Department;
import com.smartcare.system.entity.Doctor;
import com.smartcare.system.exception.ResourceNotFoundException;
import com.smartcare.system.repository.DepartmentRepository;
import com.smartcare.system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // 1. Save Doctor
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // 2. Get All Doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // 3. Get Doctor by ID
    public Doctor getDoctorById(Integer id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
    }

    // 4. Update Doctor
    public Doctor updateDoctor(Integer id, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(id);
        doctor.setFullName(doctorDetails.getFullName());
        doctor.setContactNumber(doctorDetails.getContactNumber());
        doctor.setQualification(doctorDetails.getQualification());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setConsultationFee(doctorDetails.getConsultationFee());
        doctor.setLicenseNumber(doctorDetails.getLicenseNumber());
        if (doctorDetails.getDepartment() != null) {
            doctor.setDepartment(doctorDetails.getDepartment());
        }
        return doctorRepository.save(doctor);
    }

    // 5. Delete Doctor
    public void deleteDoctor(Integer id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }

    // 6. Assign Doctor to Department
    public Doctor assignDoctorToDepartment(Integer doctorId, Integer departmentId) {
        Doctor doctor = getDoctorById(doctorId);
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + departmentId));
        doctor.setDepartment(department);
        return doctorRepository.save(doctor);
    }
}