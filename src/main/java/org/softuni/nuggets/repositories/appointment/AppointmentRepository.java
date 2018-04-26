package org.softuni.nuggets.repositories.appointment;

import org.softuni.nuggets.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Appointment findFirstByEmployeesId(String employeesId);
}
