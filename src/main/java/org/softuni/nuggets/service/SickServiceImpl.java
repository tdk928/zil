package org.softuni.nuggets.service;

import org.softuni.nuggets.entities.Sick;
import org.softuni.nuggets.repositories.sick.SickRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SickServiceImpl implements SickService{
    private final SickRepository sickRepository;

    public SickServiceImpl(SickRepository sickRepository) {
        this.sickRepository = sickRepository;
    }

    @Override
    public boolean save(Sick sick) {
        this.sickRepository.save(sick);
        return true;
    }
}
