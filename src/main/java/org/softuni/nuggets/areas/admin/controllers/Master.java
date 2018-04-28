package org.softuni.nuggets.areas.admin.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.nuggets.areas.admin.services.AdminService;
import org.softuni.nuggets.areas.admin.services.EventService;
import org.softuni.nuggets.areas.admin.services.NeededEmployersService;
import org.softuni.nuggets.areas.admin.services.TeamService;
import org.softuni.nuggets.controllers.BaseController;
import org.softuni.nuggets.entities.Employee;
import org.softuni.nuggets.entities.NeededEmployer;
import org.softuni.nuggets.entities.Team;
import org.softuni.nuggets.models.binding.NeededEmployersBindingModel;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.softuni.nuggets.contants.Constans.HOME_VIEW;

@Controller
public class Master extends BaseController {
    public static final int DAYS_IN_WEEK = 7;
    public static final int MY_BIRTHDAY = 14;
    private final AdminService adminService;
    private final ModelMapper modelMapper;
    private final NeededEmployersService neededEmployersService;
    private final EventService eventService;
    private final TeamService teamService;

    public Master(AdminService adminService, ModelMapper modelMapper, NeededEmployersService neededEmployersService, EventService eventService, TeamService teamService) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
        this.neededEmployersService = neededEmployersService;
        this.eventService = eventService;
        this.teamService = teamService;
    }

    @GetMapping("/admin/master")
    public ModelAndView initializeWorkProgram() {
        return this.view("/admin/master");
    }

    @PostMapping("/admin/master")
    public ModelAndView initializeWorkProgramConfirm(NeededEmployersBindingModel bindingModel, BindingResult bindingResult) {
        int employersCount = this.adminService.employersCount();
        if (employersCount > bindingModel.getMaxEmployers() || bindingResult.hasErrors()) {
            //todo добави error
            return this.view("/admin/master");
        }
        this.neededEmployersService.remove();
        NeededEmployer neededEmployer = this.modelMapper.map(bindingModel, NeededEmployer.class);
        neededEmployer.setCurrentEmployers(this.adminService.getAllEmployers().size());
        this.neededEmployersService.save(neededEmployer);

        int employersInTeam = employersCount / DAYS_IN_WEEK;
        this.busterLogic(employersInTeam, employersCount);

        return this.view(HOME_VIEW);
    }

    private void busterLogic(int employersInTeam, int employersCount) {
        for (int i = 1; i <= 7; i++) {
            if (i == 7) {
                int lastTeam = (int) Math.ceil(employersCount / DAYS_IN_WEEK);
                int allOther = employersCount - lastTeam;
                List<Employee> last = this.adminService.skipAndGetProportion(lastTeam * (i - 1), allOther);
                Team currentTeam = this.teamService.getTeam("team" + i);
                currentTeam.setResidue(this.addATeamProgram(last, currentTeam.getResidue()));
                continue;
            }
            List<Employee> team = this.adminService.skipAndGetProportion(employersInTeam * i, employersInTeam);
            Team currentTeam = this.teamService.getTeam("team" + i);
            currentTeam.setResidue(this.addATeamProgram(team, currentTeam.getResidue()));
        }
    }

    private int addATeamProgram(List<Employee> employees, int start) {
        boolean isGetMonth = false;
        int daysOfMonth = 0;
        int constant = 0;
        int endOfMonth = 0;
        int lastDayOfFourthWeek = 0;
        for (Employee emp : employees) {
            for (int i = start; i < start + 5; i++) {
                int count = i;
                for (int j = 1; j <= 4; j++) {
                    String json = null;
                    if (count < 10) {
                        json =
                                "{\"title\":\"ON WORK\",\"description\":\"ON WORK\",\"start\":\"2018-05-0" +
                                        count + "T11:00:00.000Z\",\"end\":\"2018-05-0" + count + "T22:00:00.000Z\"}";
                    } else {
                        json =
                                "{\"title\":\"ON WORK\",\"description\":\"ON WORK\",\"start\":\"2018-05-" +
                                        count + "T11:00:00.000Z\",\"end\":\"2018-05-" + count + "T22:00:00.000Z\"}";
                    }

                    if (!isGetMonth) {
                        daysOfMonth = this.getDaysOfMonth(json);
                        lastDayOfFourthWeek = (start + 7) * 4;
                        endOfMonth = daysOfMonth;

                        isGetMonth = true;
                    }

                    EmployeeServiceModel em = this.modelMapper.map(emp, EmployeeServiceModel.class);
                    this.eventService.addEvent(json, em);
                    count += 7;
                }
            }

            int allWorkedDays = (start + 4) + 21+2;
            constant = daysOfMonth-allWorkedDays+1;

            for (int currentDayOfMonth = allWorkedDays+1; currentDayOfMonth <= daysOfMonth; currentDayOfMonth++) {
                String json =
                        "{\"title\":\"ON WORK\",\"description\":\"ON WORK\",\"start\":\"2018-05-" +
                                currentDayOfMonth + "T11:00:00.000Z\",\"end\":\"2018-05-" + currentDayOfMonth + "T22:00:00.000Z\"}";

                EmployeeServiceModel em = this.modelMapper.map(emp, EmployeeServiceModel.class);
                this.eventService.addEvent(json, em);
            }

        }

        return constant;
    }

    private int getDaysOfMonth(String json) {
        String monthPattern = "start.{8}([0-9]{2})";
        Pattern pattern = Pattern.compile(monthPattern);
        Matcher matcher = pattern.matcher(json);
        int month = 0;
        if (matcher.find()) {
            month = Integer.parseInt(matcher.group(1));
        }

        Calendar mycal =
                new GregorianCalendar(2018, month + 1, MY_BIRTHDAY);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }
}
