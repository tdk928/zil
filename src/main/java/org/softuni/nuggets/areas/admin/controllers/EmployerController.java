package org.softuni.nuggets.areas.admin.controllers;

import org.softuni.nuggets.areas.admin.services.TestService;
import org.softuni.nuggets.controllers.BaseController;
import org.softuni.nuggets.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.softuni.nuggets.areas.contants.Constans.ALL_EMPLOYERS;

@Controller
public class EmployerController extends BaseController{
    private TestService testService;

    public EmployerController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(ALL_EMPLOYERS)
    public ModelAndView allEmployers(@PageableDefault(size = 10) org.springframework.data.domain.Pageable pageable) {
        Page<Employee> results = this.testService.findAllByPage(pageable);
        int longer = results.getTotalPages();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("allEmployers");
        mav.addObject("longer",longer);
        mav.addObject("allEmployers",results);
        return mav;
    }
}
