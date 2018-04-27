package org.softuni.nuggets.entities;

import javax.persistence.*;
import java.time.LocalDate;
import static org.softuni.nuggets.areas.contants.Constans.APPOINTMENTS_TABLE;

@Entity
@Table(name = APPOINTMENTS_TABLE)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne
    private Employee employees;

    private LocalDate start;

    private LocalDate end;

    public Appointment() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployees() {
        return this.employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
