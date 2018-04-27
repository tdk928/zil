package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService {
    Page<Employee> findAllByPage(Pageable pageable);

    default long getTotalPages() {
        return getTotalPages(10);
    }

    long getTotalPages(int size);
}
