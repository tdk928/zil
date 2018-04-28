package org.softuni.nuggets.areas.admin.repositories;

import org.softuni.nuggets.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();

//    @Query(value = "SELECT * FROM `event` AS e JOIN employers AS em WHERE em.id = :id",nativeQuery = true)
//    List<Event> getAllEventsByEmployerId(@Param("id")String id);

    Event save(Event event);

    void delete(Event event);


    @Query("select b from Event  b " +
            "WHERE b.start between ?1 and ?2 and b.end between ?1 and ?2 ")
    List<Event> findByDatesBetween(Date start, Date end);

}
