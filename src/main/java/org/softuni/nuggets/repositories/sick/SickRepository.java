package org.softuni.nuggets.repositories.sick;

import org.softuni.nuggets.entities.Sick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SickRepository extends JpaRepository<Sick,Long>{
}
