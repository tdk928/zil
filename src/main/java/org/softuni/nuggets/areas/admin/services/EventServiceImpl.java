package org.softuni.nuggets.areas.admin.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.nuggets.areas.admin.exceptions.BadTimeFormatException;
import org.softuni.nuggets.areas.admin.repositories.EventRepository;
import org.softuni.nuggets.entities.Event;
import org.softuni.nuggets.models.binding.EventAddBindingModel;
import org.softuni.nuggets.models.binding.EventRemoveBindingModel;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.softuni.nuggets.contants.Constans.BAD_END_DATE;
import static org.softuni.nuggets.contants.Constans.BAD_START_DATE;
import static org.softuni.nuggets.contants.Constans.DATE_PATTERN;

@Service
@Transactional
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final AdminService adminService;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper, AdminService adminService) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.adminService = adminService;
    }

    public boolean addEvent(String json,EmployeeServiceModel employer) {
        EventAddBindingModel event = new Gson().fromJson(json, EventAddBindingModel.class);
        Event currentEvent = this.modelMapper.map(event, Event.class);
        this.eventRepository.save(currentEvent);

        employer.addEvent(currentEvent);
        this.adminService.save(employer);
        return true;
    }

    @Override
    public boolean deleteEvent(String json, EmployeeServiceModel employer) {
        EventRemoveBindingModel event = new Gson().fromJson(json, EventRemoveBindingModel.class);
        Event currentEvent = this.modelMapper.map(event, Event.class);
        employer.deleteEvent(currentEvent);
        this.adminService.save(employer);
        this.eventRepository.delete(currentEvent);
        return true;
    }

    @Override
    public boolean updateEvent(String json, EmployeeServiceModel employer) {
        EventAddBindingModel event = new Gson().fromJson(json, EventAddBindingModel.class);
        Event currentEvent = this.modelMapper.map(event, Event.class);
        this.eventRepository.save(currentEvent);
        return true;
    }

    @Override
    public boolean deleteEvent(Event event) {
        this.eventRepository.delete(event);
        return true;
    }

    @Override
    public List<Event> getEventsInRange(Principal principal, String start, String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat(DATE_PATTERN);

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadTimeFormatException(BAD_START_DATE + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadTimeFormatException(BAD_END_DATE + end);
        }

        EmployeeServiceModel employer = this.adminService.getByUsername(principal.getName());
        return employer.getEvents();
    }

    @Override
    public List<Event> getEventsInRange(String username,String start, String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat(DATE_PATTERN);

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadTimeFormatException(BAD_START_DATE + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadTimeFormatException(BAD_END_DATE + end);
        }

        EmployeeServiceModel employer = this.adminService.getByUsername(username);
        return employer.getEvents();
    }

    @Override
    public Event save(Event event) {
        return this.eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }


}
