package org.softuni.nuggets.areas.admin.controllers;

import org.softuni.nuggets.areas.admin.exceptions.BadTimeFormatException;
import org.softuni.nuggets.areas.admin.services.AdminService;
import org.softuni.nuggets.areas.admin.services.EventService;
import org.softuni.nuggets.controllers.BaseController;
import org.softuni.nuggets.entities.Event;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.softuni.nuggets.contants.Constans.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class EventController extends BaseController {

    private final EventService eventService;

    private final AdminService adminService;

    public EventController(EventService eventService, AdminService adminService) {
        this.eventService = eventService;
        this.adminService = adminService;
    }

    @RequestMapping(value = ADD_EVENT, method = RequestMethod.POST)
    public void addEvent(@RequestParam(JSON) String json,Principal principal) {
        EmployeeServiceModel employer = this.adminService.getByUsername(principal.getName());
        this.eventService.addEvent(json,employer);
    }

    @RequestMapping(value = REMOVE_EVENT, method = RequestMethod.POST)
    public void removeEvent(@RequestParam(JSON) String json,Principal principal) {
        EmployeeServiceModel employer = this.adminService.getByUsername(principal.getName());
        this.eventService.deleteEvent(json,employer);
    }

    @RequestMapping(value = UPDATE_EVENT, method = RequestMethod.PATCH)
    public void updateEvent(@RequestParam(JSON) String json,Principal principal) {
        EmployeeServiceModel employer = this.adminService.getByUsername(principal.getName());
        this.eventService.updateEvent(json,employer);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @RequestMapping(value=ALL_EVENTS, method= RequestMethod.GET)
    public List<Event> getEventsInRange(Principal principal,@RequestParam(value = START, required = true) String start,
                                        @RequestParam(value = END, required = true) String end) {
       return this.eventService.getEventsInRange(principal,start,end);
    }

//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @RequestMapping(value=ALL_EVENTS, method= RequestMethod.GET)
//    public List<Event> getEventsInRange(String username,@RequestParam(value = START, required = true) String start,
//                                        @RequestParam(value = END, required = true) String end) {
//        return this.eventService.getEventsInRange(username,start,end);
//    }

//    @RequestMapping(value = "/ttt", method = RequestMethod.GET)
//    public void show() {
//
//    }
}