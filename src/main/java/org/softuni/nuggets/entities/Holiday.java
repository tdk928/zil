package org.softuni.nuggets.entities;

import javax.persistence.*;

@Entity
@Table(name = "holiday_days")
public class Holiday {
    private static final int MAX_HOLIDAY_Day_IN_YEAR = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Employee employee;

    private int leaveDay;

    public Holiday() {
        this.leaveDay = MAX_HOLIDAY_Day_IN_YEAR;
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

    public int getLeaveDay() {
        return this.leaveDay;
    }

    public void setLeaveDay(int leaveDay) {
        this.leaveDay = leaveDay;
    }
}
