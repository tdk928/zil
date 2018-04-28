package org.softuni.nuggets.areas.admin.repositories;

import org.softuni.nuggets.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PaginationRepository extends Repository<Employee,String> {
    Page<Employee> findByDeletedOnIsNull(Pageable pageable);

}
