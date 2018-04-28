package org.softuni.nuggets.areas.admin.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.nuggets.areas.admin.services.AdminService;
import org.softuni.nuggets.areas.admin.services.NeededEmployersService;
import org.softuni.nuggets.cloud.cloud.CloudImageExtractor;
import org.softuni.nuggets.controllers.BaseController;
import org.softuni.nuggets.entities.Image;
import org.softuni.nuggets.entities.NeededEmployer;
import org.softuni.nuggets.entities.Role;

import org.softuni.nuggets.models.binding.AdminEditEmployeeBindingModel;
import org.softuni.nuggets.models.binding.RegisterBindingModel;
import org.softuni.nuggets.models.binding.SearchBindingModel;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.softuni.nuggets.contants.Constans.*;


@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = ADMIN_ROUTE)
public class AdminController extends BaseController {
    private final NeededEmployersService neededEmployersService;
    private final AdminService adminService;
    private final CloudImageExtractor cloudImageExtractor;

    public AdminController(NeededEmployersService neededEmployersService, AdminService adminService, CloudImageExtractor cloudImageExtractor) {
        this.neededEmployersService = neededEmployersService;

        this.adminService = adminService;
        this.cloudImageExtractor = cloudImageExtractor;
    }

//    @GetMapping(EVENT)
//    public ModelAndView showEmployerEvent(@PathVariable(USERNAME) String username, Model model, ModelMapper modelMapper) {
////        EmployeeServiceModel employeeByUsername = this.adminService.getByUsername(username);
////
////        for (Role role : employeeByUsername.getAuthorities()) {
////            if(role.getAuthority().equals("ROLE_ADMIN")) {
////                employeeByUsername.setIsAdmin(true);
////                break;
////            }
////        }
////
////        if (!model.containsAttribute(EMPLOYER_INPUT)) {
////            AdminEditEmployeeBindingModel bindingModel = modelMapper.map(employeeByUsername, AdminEditEmployeeBindingModel.class);
////
////            model.addAttribute(EMPLOYER_INPUT, bindingModel);
////        }
////
////        return this.view(EDIT_VIEW);
//        return null;
//    }

    @GetMapping(REGISTER_ROUTE)
    public ModelAndView register(Model model) {
        if (!model.containsAttribute(EMPLOYER_INPUT)) {
            model.addAttribute(EMPLOYER_INPUT, new RegisterBindingModel());
        }

        return this.view(ADMIN_REGISTER);
    }

    @GetMapping(SEARCH)
    public ModelAndView search() {
        return this.view("/admin/search");
    }

    @PostMapping(SEARCH)
    public ModelAndView searchConfirm(SearchBindingModel searchBindingModel)  {
        EmployeeServiceModel employee = null;
        try {
            employee = this.adminService.getByUsername(searchBindingModel.getUsername());
        } catch (Exception e) {
            return this.redirect("/admin/search");
        }

        return this.view(DETAILS).addObject("employee",employee);
    }

    @GetMapping("/details")
    public ModelAndView details() {
        return this.view(INDEX_VIEW);
    }

    @PostMapping(REGISTER_ROUTE)
    public ModelAndView registerConfirm(@Valid @ModelAttribute(name = EMPLOYER_INPUT) RegisterBindingModel bindingModel,
                                        BindingResult bindingResult,RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(EMPLOYER_INPUT_VALIDATION, bindingResult);
            redirectAttributes.addFlashAttribute(EMPLOYER_INPUT, bindingModel);
            return this.redirect(ADMIN_REGISTER);
        } else {
            if (bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
                NeededEmployer neededUtilEntity = this.neededEmployersService.getNeededUtilEntity();

                if (!neededUtilEntity.increment()) {
                    throw new Exception("We have max employers at this moment.");
                }

                this.adminService.register(bindingModel);
                this.neededEmployersService.save(neededUtilEntity);
            }
        }
        return this.redirect(HOME_VIEW);
    }

    @GetMapping(EDIT_ROUTE)
    public ModelAndView editEmployee(@PathVariable(USERNAME) String username, Model model, ModelMapper modelMapper) throws IOException {
        EmployeeServiceModel employeeByUsername = this.adminService.getByUsername(username);

        List<Image> images = new ArrayList<Image>();
        Image currentEmployeeImage = null;
        try {
            images = this.cloudImageExtractor.getAllImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Image> list = new ArrayList<>();
        List<Image> no = new ArrayList<>();
        for (Image image : images) {
            if(image.getName().equals(username)) {
                list.add(image);
                break;
            } else if(image.getName().equals("no.png")){
                no.add(image);
            }
        }

        if(list.size() == 0) {
            list.add(no.get(0));
        }

        for (Role role : employeeByUsername.getAuthorities()) {
            if(role.getAuthority().equals("ROLE_ADMIN")) {
                employeeByUsername.setIsAdmin(true);
                break;
            }
        }

        if (!model.containsAttribute(EMPLOYER_INPUT)) {
            AdminEditEmployeeBindingModel bindingModel = modelMapper.map(employeeByUsername, AdminEditEmployeeBindingModel.class);

            model.addAttribute(EMPLOYER_INPUT, bindingModel);
            model.addAttribute("imageData", list);
        }

        return this.view(EDIT_VIEW).addObject("imageData",list);
    }

    @PostMapping(EDIT_ROUTE)
    public ModelAndView editEmployerForm(@PathVariable String username, @ModelAttribute(name = EMPLOYER_INPUT) AdminEditEmployeeBindingModel editEmployeeBindingModel,
                                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(EMPLOYER_INPUT_VALIDATION, bindingResult);
            redirectAttributes.addFlashAttribute(EMPLOYER_INPUT, editEmployeeBindingModel);
            return this.redirect(HOME_VIEW);
        } else {
            this.adminService.editEmployer(username, editEmployeeBindingModel);
            return this.redirect(HOME_VIEW);
        }


    }

    @GetMapping(DELETE_ROUTE)
    public ModelAndView deleteEmployer(@PathVariable String username, Model model, ModelMapper modelMapper) {
        EmployeeServiceModel employeeByUsername = this.adminService.getByUsername(username);

        model.addAttribute(EMPLOYER_INPUT, modelMapper.map(employeeByUsername, AdminEditEmployeeBindingModel.class));

        return this.view(REMOVE_EMPLOYER);
    }

    @PostMapping(DELETE_ROUTE)
    public ModelAndView removeConfirm(@PathVariable String username, Principal principal) throws Exception {
        if(principal.getName().equals(username)) {
            throw new Exception("You cant delete yourself.");
        }
        NeededEmployer neededUtilEntity = this.neededEmployersService.getNeededUtilEntity();
        if (!neededUtilEntity.decrement()) {
            throw new Exception("Employers in company cant be negative number.");
        }
        this.adminService.removeEmployer(username);
        this.neededEmployersService.save(neededUtilEntity);
        return this.redirect(HOME_VIEW);
    }

}