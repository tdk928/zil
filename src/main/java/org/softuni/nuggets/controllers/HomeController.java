package org.softuni.nuggets.controllers;

import org.softuni.nuggets.areas.user.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static org.softuni.nuggets.contants.Constans.*;

@Controller
public class HomeController extends BaseController{
    private EmployeeService employeeService;

    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(INDEX)
    public ModelAndView index() {
       return this.view(INDEX_VIEW);
    }

    @GetMapping(HOME)
    public ModelAndView home() {
       return this.view(HOME_VIEW);
    }

    @GetMapping(LOGIN)
//    @PreAuthorize("isAnonymous()")
    public ModelAndView login(@RequestParam(required = false, name = ERROR) String error, Principal principal) {
        if(principal != null) {
            return this.redirect(HOME_VIEW);
        }

        if (error != null) {
            this.view(LOGIN_VIEW, ERROR, error);
        }

        return this.view(LOGIN_VIEW);
    }
//    @RequestMapping("/404.html")
//    public String render404(Model model) {
//        // Add model attributes
//        System.out.println();
//        return "404";
//    }

}
