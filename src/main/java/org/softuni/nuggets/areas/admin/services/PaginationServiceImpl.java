package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.areas.admin.repositories.PaginationRepository;
import org.softuni.nuggets.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PaginationServiceImpl implements PaginationService {
    private PaginationRepository testRepository;

    public PaginationServiceImpl(PaginationRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Page<Employee> findAllByPage(Pageable pageable) {
        return this.testRepository.findByDeletedOnIsNull(pageable);
    }

    //hardcode , не се използва вече...
    @Override
    public long getTotalPages(int size) {
        return 16;
    }



}
