package org.softuni.nuggets.areas.admin.repositories;

import org.softuni.nuggets.entities.Employee;
import org.softuni.nuggets.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Employee,String>{
    Employee findFirstByUsernameAndDeletedOnIsNull(String employee);

    List<Employee> getAllByDeletedOnIsNull();

    @Query(value = "SELECT * FROM Employers AS e WHERE e.deleted_on IS NULL LIMIT :num",nativeQuery = true)
    List<Employee> getProportion(@Param("num")int num);

    @Query(value = "SELECT * FROM Employers AS e WHERE e.deleted_on IS NULL LIMIT :skip,:take",nativeQuery = true)
    List<Employee> skipAndGetProportion(@Param("skip")int skip, @Param("take")int take);

//    @Query(value = "SELECT * FROM Employers AS e" +
//            "JOIN Appointment AS a" +
//            "ON e.id = a.employees_id " +
//            "WHERE e.deleted_on IS NULL " +
//            "ORDER BY a.`start` " +
//            "LIMIT :skip,:take",nativeQuery = true)
//    List<Employee> skipAndGetProportion(@Param("skip")int skip, @Param("take")int take);
}
