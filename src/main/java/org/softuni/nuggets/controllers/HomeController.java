package org.softuni.nuggets.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static org.softuni.nuggets.areas.contants.Constans.*;

@Controller
public class HomeController extends BaseController{
    @GetMapping(INDEX)
    public ModelAndView index() {
       return this.view(INDEX_VIEW);
    }

    @GetMapping(HOME)
    public ModelAndView home() {
       return this.view(HOME_VIEW);
    }

    @GetMapping("/asd")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public String asd() {
        return "VSICHKO GRUMNA!";
    }
}
