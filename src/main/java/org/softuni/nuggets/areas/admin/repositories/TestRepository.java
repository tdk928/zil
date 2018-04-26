package org.softuni.nuggets.areas.admin.repositories;

import org.softuni.nuggets.entities.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestRepository extends PagingAndSortingRepository<Employee,String> {
}
