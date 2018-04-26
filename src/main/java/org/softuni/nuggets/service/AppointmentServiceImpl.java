package org.softuni.nuggets.service;

import org.softuni.nuggets.entities.Appointment;
import org.softuni.nuggets.repositories.appointment.AppointmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public boolean save(Appointment appointment) {
        this.appointmentRepository.save(appointment);
        return true;
    }

    @Override
    public Appointment findFirstByEmployeesId(String employeesId) {
        return this.appointmentRepository.findFirstByEmployeesId(employeesId);
    }


}
