package org.softuni.nuggets.areas.admin.controllers;

import org.softuni.nuggets.areas.admin.services.TestService;
import org.softuni.nuggets.controllers.BaseController;
import org.softuni.nuggets.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.softuni.nuggets.areas.contants.Constans.ALL_EMPLOYERS;

@Controller
@PreAuthorize("hasRole('ADMIN')")
//@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class EmployerController extends BaseController{
    private static final int EMPLOYERS_IN_PAGE = 10;
    private static final int MATH_CEIL = 1;
    private TestService testService;

    public EmployerController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(ALL_EMPLOYERS)
    public ModelAndView allEmployers(@PageableDefault(size = EMPLOYERS_IN_PAGE) org.springframework.data.domain.Pageable pageable) {
        Page<Employee> result = this.testService.findAllByPage(pageable);


        int longer = (result.getTotalPages()/ EMPLOYERS_IN_PAGE)+ MATH_CEIL;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/all-employers");
        mav.addObject("longer",longer);
        mav.addObject("allEmployers",result);
        return mav;
    }
}
