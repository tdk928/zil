package org.softuni.nuggets.areas.admin.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.nuggets.areas.admin.services.AdminService;
import org.softuni.nuggets.controllers.BaseController;
import org.softuni.nuggets.models.binding.AdminEditEmployeeBindingModel;
import org.softuni.nuggets.models.binding.RegisterBindingModel;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static org.softuni.nuggets.areas.contants.Constans.*;


@Controller
@RequestMapping(value = ADMIN_ROUTE)
public class AdminController extends BaseController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {

        this.adminService = adminService;
    }

    @GetMapping(REGISTER_ROUTE)
    public ModelAndView register(Model model) {
        if (!model.containsAttribute(EMPLOYER_INPUT)) {
            model.addAttribute(EMPLOYER_INPUT, new RegisterBindingModel());
        }

        return this.view(ADMIN_REGISTER);
    }

    @PostMapping(REGISTER_ROUTE)
    public ModelAndView registerConfirm(@Valid @ModelAttribute RegisterBindingModel bindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.redirect(ADMIN_REGISTER);
        } else {
            if (bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
                this.adminService.register(bindingModel);
            }
        }
        return this.redirect(HOME);
    }

    @GetMapping(EDIT_ROUTE)
    public ModelAndView editEmployee(@PathVariable(USERNAME) String username, Model model, ModelMapper modelMapper) {
        EmployeeServiceModel employeeByUsername = this.adminService.getByUsername(username);

        if (!model.containsAttribute(EMPLOYER_INPUT)) {
            AdminEditEmployeeBindingModel bindingModel = modelMapper.map(employeeByUsername, AdminEditEmployeeBindingModel.class);

            model.addAttribute(EMPLOYER_INPUT, bindingModel);
        }

        return this.view(EDIT);
    }

    @PostMapping(EDIT_ROUTE)
    public ModelAndView editEmployerForm(@PathVariable String username, @ModelAttribute(name = EMPLOYER_INPUT) AdminEditEmployeeBindingModel editEmployeeBindingModel,
                                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(EMPLOYER_INPUT_VALIDATION, bindingResult);
            redirectAttributes.addFlashAttribute(EMPLOYER_INPUT, editEmployeeBindingModel);

            return this.redirect(HOME);
        } else {
            this.adminService.editEmployer(username, editEmployeeBindingModel);
            return this.redirect(ALL_EMPLOYERS_VIEW);
        }


    }

    @GetMapping(DELETE_ROUTE)
    public ModelAndView deleteEmployer(@PathVariable String username, Model model, ModelMapper modelMapper) {
        EmployeeServiceModel employeeByUsername = this.adminService.getByUsername(username);

        model.addAttribute(EMPLOYER_INPUT, modelMapper.map(employeeByUsername, AdminEditEmployeeBindingModel.class));

        return this.view(REMOVE_EMPLOYER);
    }

    @PostMapping(DELETE_ROUTE)
    public ModelAndView removeConfirm(@PathVariable String username) {
        this.adminService.removeEmployer(username);
        return this.redirect(ALL_EMPLOYERS_VIEW);
    }

}