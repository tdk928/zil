package org.softuni.nuggets.entities;

import javax.persistence.*;

import static org.softuni.nuggets.areas.contants.Constans.SICK_TABLE;

@Entity
@Table(name = SICK_TABLE)
public class Sick {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Employee employee;

    private int sickDays;

    public Sick() {
    }

    public long getId() {
        return this.id;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSickDays() {
        return this.sickDays;
    }

    public void setSickDays(int sickDays) {
        this.sickDays = sickDays;
    }

    public void add() {
        this.setSickDays(this.getSickDays()+1);
    }

    public void add(int sickDay) {
        this.setSickDays(this.getSickDays()+sickDay);
    }
}
