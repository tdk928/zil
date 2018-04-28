package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.entities.NeededEmployer;

public interface NeededEmployersService {
    NeededEmployer getNeededUtilEntity();

    int getCurrentlyEmployersInCompany();

    boolean save(NeededEmployer neededEmployer);

    int getNeededEmployersTableSize();

    boolean remove();
}
