package org.softuni.nuggets.service;

import org.softuni.nuggets.entities.Appointment;

public interface AppointmentService {
    boolean save(Appointment appointment);

    Appointment findFirstByEmployeesId(String employeesId);

}
