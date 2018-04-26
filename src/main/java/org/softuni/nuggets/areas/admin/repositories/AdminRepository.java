package org.softuni.nuggets.areas.admin.repositories;

import org.softuni.nuggets.entities.Employee;
import org.softuni.nuggets.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Employee,String>{
    Employee findFirstByUsernameAndDeletedOnIsNull(String employee);

    List<Employee> getAllByDeletedOnIsNull();

}
