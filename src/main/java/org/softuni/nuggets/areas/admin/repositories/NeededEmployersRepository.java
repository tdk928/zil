package org.softuni.nuggets.areas.admin.repositories;

import org.softuni.nuggets.entities.NeededEmployer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeededEmployersRepository extends JpaRepository<NeededEmployer,Long> {
    NeededEmployer getFirstById(long id);
}
