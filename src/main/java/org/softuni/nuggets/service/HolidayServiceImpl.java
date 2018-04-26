package org.softuni.nuggets.service;

import org.softuni.nuggets.entities.Holiday;
import org.softuni.nuggets.repositories.holiday.HolidayRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HolidayServiceImpl implements HolidayService{
    private final HolidayRepository holidayRepository;

    public HolidayServiceImpl(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @Override
    public boolean save(Holiday holiday) {
        this.holidayRepository.save(holiday);
        return true;
    }
}
