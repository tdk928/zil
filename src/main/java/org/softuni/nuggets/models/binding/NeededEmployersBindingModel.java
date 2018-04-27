package org.softuni.nuggets.models.binding;

import javax.validation.constraints.Min;

public class NeededEmployersBindingModel {
    private long id;

    private int currentEmployers;

    @Min(value = 1)
    private int maxEmployers;


    public NeededEmployersBindingModel() {
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

    public void setMaxEmployers(int maxEmployers) {
        this.maxEmployers = maxEmployers;
    }

}
