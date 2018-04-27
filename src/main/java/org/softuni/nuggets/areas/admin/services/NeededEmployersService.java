package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.entities.NeededEmployer;

public interface NeededEmployersService {
    NeededEmployer getNeededUtilEntity();

    boolean save(NeededEmployer neededEmployer);

    int getNeededEmployersTableSize();

    boolean remove();
}
