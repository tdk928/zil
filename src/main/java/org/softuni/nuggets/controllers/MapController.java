package org.softuni.nuggets.controllers;

import org.softuni.nuggets.service.MapService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapController {
    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/map")
    public ModelAndView map(ModelAndView modelAndView) {
        String geoJson = this.mapService.getMap();

        modelAndView.setViewName("map");
        modelAndView.addObject("geoJson", geoJson);

        return modelAndView;
    }
}
