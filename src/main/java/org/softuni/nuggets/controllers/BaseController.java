package org.softuni.nuggets.controllers;

import org.springframework.web.servlet.ModelAndView;

import static org.softuni.nuggets.areas.contants.Constans.REDIRECT;

public abstract class BaseController {
    protected ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    protected ModelAndView view(String view, String name, Object model) {
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(name, model);
        return modelAndView;
    }

    protected ModelAndView redirect(String view) {
        return new ModelAndView(REDIRECT + view);
    }
}
