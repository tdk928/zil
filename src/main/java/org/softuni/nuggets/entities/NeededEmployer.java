package org.softuni.nuggets.entities;

import javax.persistence.*;

import static org.softuni.nuggets.contants.Constans.NEEDED_EMPLOYERS;

@Entity
@Table(name = NEEDED_EMPLOYERS)
public class NeededEmployer {
    public static final String CURRENT_EMPLOYERS = "current_employers";
    public static final String MAX_EMPLOYERS = "max_employers";
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final String WORKED_DAYS = "worked_days";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = CURRENT_EMPLOYERS)
    private int currentEmployers;

    @Column(name = MAX_EMPLOYERS)
    private int maxEmployers;


    public NeededEmployer(){

    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCurrentEmployers() {
        return this.currentEmployers;
    }

    public void setCurrentEmployers(int currentEmployers) {
        this.currentEmployers = currentEmployers;
    }

    public int getMaxEmployers() {
        return this.maxEmployers;
    }

    public void setMaxEmployers(int maxEmployers)  {
        this.maxEmployers = maxEmployers;
    }


    public boolean increment() {
        int current = this.getCurrentEmployers()+ONE;
        if(current > this.getMaxEmployers()) {
            return false;
        }
        this.setCurrentEmployers(current);
        return true;
    }

    public boolean decrement() {
        int current = this.getCurrentEmployers()- ONE;
        if(current < ZERO){
            return false;
        }
        this.setCurrentEmployers(current);
        return true;
    }
}
