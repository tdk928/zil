package org.softuni.nuggets.areas.admin.controllers;

import org.softuni.nuggets.areas.admin.exceptions.BadTimeFormatException;
import org.softuni.nuggets.areas.admin.services.EventService;
import org.softuni.nuggets.areas.user.services.EmployeeService;
import org.softuni.nuggets.controllers.BaseController;
import org.softuni.nuggets.entities.Event;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EventController extends BaseController {

    private final EventService eventService;

    private final EmployeeService userService;

    public EventController(EventService eventService, EmployeeService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    public void addEvent(@RequestParam("json") String json,Principal principal) {
        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());
        this.eventService.addEvent(json,employer);
    }

    @RequestMapping(value = "/removeevent", method = RequestMethod.POST)
    public void removeEvent(@RequestParam("json") String json,Principal principal) {
        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());
        this.eventService.deleteEvent(json,employer);
    }

    @RequestMapping(value = "/updateevent", method = RequestMethod.PATCH)
    public void updateEvent(@RequestParam("json") String json,Principal principal) {
        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());
        this.eventService.updateEvent(json,employer);
    }


    @RequestMapping(value="/events", method= RequestMethod.GET)
    public List<Event> getEventsInRange(Principal principal,@RequestParam(value = "start", required = true) String start,
                                        @RequestParam(value = "end", required = true) String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadTimeFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadTimeFormatException("bad end date: " + end);
        }

        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());
//        return eventRepository.findByDatesBetween(startDate, endDate);
        return employer.getEvents();
    }
}