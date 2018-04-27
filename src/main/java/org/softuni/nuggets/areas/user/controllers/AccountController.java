package org.softuni.nuggets.areas.user.controllers;



import org.softuni.nuggets.areas.user.services.EmployeeService;
import org.softuni.nuggets.controllers.BaseController;
import org.softuni.nuggets.models.binding.UserEditEmployeeBindingModel;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import static org.softuni.nuggets.areas.contants.Constans.*;


@Controller
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class AccountController extends BaseController {
    private final EmployeeService userService;


    @Autowired
    public AccountController(EmployeeService userService) {
        this.userService = userService;
    }


    @PostMapping(LOGOUT)
    public ModelAndView logout(@RequestParam(required = false, name = "logout") String logout, RedirectAttributes redirectAttributes) {
        if (logout != null) {
            redirectAttributes.addFlashAttribute("logout", logout);
        }

        return this.redirect(LOGIN);
    }

    @GetMapping(CHANGE_PASSWORD)
    public ModelAndView editEmployer(Principal principal) {
        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());
        return this.view(CHANGE_PASSWORD_VIEW).addObject("employer",employer);
    }

    @PostMapping(CHANGE_PASSWORD)
    public ModelAndView editEmployer(UserEditEmployeeBindingModel model,Principal principal) {

        Authentication request = new UsernamePasswordAuthenticationToken(
                model.getUsername(),
                model.getPassword());

        SecurityContextHolder.getContext().setAuthentication(request);
        EmployeeServiceModel employer = this.userService.getByUsernameAndDeletedOnIsNotNull(principal.getName());

        this.userService.editEmployer(employer.getUsername(),model);
        return this.redirect(HOME_VIEW);
    }




    @GetMapping(CALENDAR)
    public ModelAndView viewCalendar() {
        return this.view(CALENDAR_VIEW);
    }



}