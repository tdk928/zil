package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.areas.admin.repositories.NeededEmployersRepository;
import org.softuni.nuggets.entities.NeededEmployer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class NeededEmployersImpl implements NeededEmployersService {
    private final NeededEmployersRepository neededEmployersRepository;

    public NeededEmployersImpl(NeededEmployersRepository neededEmployersRepository) {
        this.neededEmployersRepository = neededEmployersRepository;
    }

    @Override
    public NeededEmployer getNeededUtilEntity() {
        return neededEmployersRepository.findAll().get(0);
    }

    @Override
    public int getCurrentlyEmployersInCompany() {
        return this.neededEmployersRepository.findAll().get(0).getCurrentEmployers();
    }

    @Override
    public boolean save(NeededEmployer neededEmployer) {
        this.neededEmployersRepository.save(neededEmployer);
        return true;
    }

    @Override
    public int getNeededEmployersTableSize() {
        return (int) this.neededEmployersRepository.count();
    }

    @Override
    public boolean remove() {
       this.neededEmployersRepository.delete(neededEmployersRepository.findAll().get(0));
       return true;
    }

}
