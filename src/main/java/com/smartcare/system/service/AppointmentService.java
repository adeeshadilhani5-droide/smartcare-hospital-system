package com.smartcare.system.service;

import com.smartcare.system.entity.Appointment;
import com.smartcare.system.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Appointment appointment) {
        // Fix 5: Prevent Appointment Conflicts
        boolean isClash = appointmentRepository.existsByDoctorDoctorIdAndAppointmentDateAndAppointmentTimeAndStatusNot(
                appointment.getDoctor().getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                "CANCELLED"
        );

        if (isClash) {
            throw new RuntimeException("Doctor is already booked for this date and time!");
        }

        if (appointment.getStatus() == null) {
            appointment.setStatus("SCHEDULED");
        }
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getDoctorSchedule(Integer doctorId) {
        return appointmentRepository.findByDoctorDoctorId(doctorId);
    }

    public Appointment updateAppointment(Integer id, Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));

        if (appointmentDetails.getAppointmentDate() != null && appointmentDetails.getAppointmentTime() != null) {
            boolean isClash = appointmentRepository.existsByDoctorDoctorIdAndAppointmentDateAndAppointmentTimeAndStatusNot(
                    appointment.getDoctor().getDoctorId(),
                    appointmentDetails.getAppointmentDate(),
                    appointmentDetails.getAppointmentTime(),
                    "CANCELLED"
            );
            if (isClash && (!appointment.getAppointmentDate().equals(appointmentDetails.getAppointmentDate())
                    || !appointment.getAppointmentTime().equals(appointmentDetails.getAppointmentTime()))) {
                throw new RuntimeException("Doctor is already booked for this new date and time!");
            }
            appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
            appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        }

        if (appointmentDetails.getConsultationRoom() != null) {
            appointment.setConsultationRoom(appointmentDetails.getConsultationRoom());
        }

        if (appointmentDetails.getStatus() != null) {
            appointment.setStatus(appointmentDetails.getStatus());
        }

        return appointmentRepository.save(appointment);
    }

    public Appointment cancelAppointment(Integer id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));

        appointment.setStatus("CANCELLED");
        return appointmentRepository.save(appointment);
    }
}