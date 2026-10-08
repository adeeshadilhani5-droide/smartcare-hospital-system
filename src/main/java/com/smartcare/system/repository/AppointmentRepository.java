package com.smartcare.system.repository;

import com.smartcare.system.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    // Status එක CANCELLED නොවන සහ එකම Doctor, Date, Time ඇති Appointments පරීක්ෂාව
    boolean existsByDoctorDoctorIdAndAppointmentDateAndAppointmentTimeAndStatusNot(
            Integer doctorId, LocalDate appointmentDate, LocalTime appointmentTime, String status);

    // Doctor ID එක අනුව Schedule එක ලබා ගැනීම
    List<Appointment> findByDoctorDoctorId(Integer doctorId);
}