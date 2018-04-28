package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.entities.Event;
import org.softuni.nuggets.models.service.EmployeeServiceModel;

import java.security.Principal;
import java.util.List;

public interface EventService {
    boolean addEvent(String json, EmployeeServiceModel employer);

    boolean deleteEvent(String json, EmployeeServiceModel employer);

    boolean updateEvent(String json, EmployeeServiceModel employer);

    boolean deleteEvent(Event event);

    List<Event> getEventsInRange(Principal principal, String start, String end);

    List<Event> getEventsInRange(String username, String start, String end);

    Event save(Event event);

    List<Event> getAllEvents();
}
