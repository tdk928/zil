package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.areas.admin.repositories.TestRepository;
import org.softuni.nuggets.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestServiceImpl implements TestService {
    private TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
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
