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

import static org.softuni.nuggets.areas.contants.Constans.*;

@RestController
public class EventController extends BaseController {

    private final EventService eventService;

    private final EmployeeService userService;

    public EventController(EventService eventService, EmployeeService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @RequestMapping(value = ADD_EVENT, method = RequestMethod.POST)
    public void addEvent(@RequestParam(JSON) String json,Principal principal) {
        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());
        this.eventService.addEvent(json,employer);
    }

    @RequestMapping(value = REMOVE_EVENT, method = RequestMethod.POST)
    public void removeEvent(@RequestParam(JSON) String json,Principal principal) {
        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());
        this.eventService.deleteEvent(json,employer);
    }

    @RequestMapping(value = UPDATE_EVENT, method = RequestMethod.PATCH)
    public void updateEvent(@RequestParam(JSON) String json,Principal principal) {
        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());
        this.eventService.updateEvent(json,employer);
    }


    @RequestMapping(value=ALL_EVENTS, method= RequestMethod.GET)
    public List<Event> getEventsInRange(Principal principal,@RequestParam(value = START, required = true) String start,
                                        @RequestParam(value = END, required = true) String end) {
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

        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());
        return employer.getEvents();
    }
}