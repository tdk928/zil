package org.softuni.nuggets.terminal;

import org.softuni.nuggets.areas.admin.services.AdminService;
import org.softuni.nuggets.areas.admin.services.NeededEmployersService;
import org.softuni.nuggets.areas.admin.services.TeamService;
import org.softuni.nuggets.entities.Employee;
import org.softuni.nuggets.entities.NeededEmployer;
import org.softuni.nuggets.entities.Role;
import org.softuni.nuggets.entities.Team;
import org.softuni.nuggets.models.binding.RegisterBindingModel;
import org.softuni.nuggets.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartUp implements CommandLineRunner{
    private final RoleService roleService;
    private final AdminService adminService;
    private final NeededEmployersService neededEmployersService;
    private final TeamService teamService;

    public StartUp(RoleService roleService, AdminService adminService, NeededEmployersService neededEmployersService, TeamService teamService) {
        this.roleService = roleService;
        this.adminService = adminService;
        this.neededEmployersService = neededEmployersService;
        this.teamService = teamService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(!this.roleService.findByAuthority("ROLE_USER")) {
          this.createRoleUser();
        }
        if(!this.roleService.findByAuthority("ROLE_ADMIN")) {
            this.createRoleAdmin();
        }
        if (this.neededEmployersService.getNeededEmployersTableSize() == 0) {
           this.createNeededEmployers();
        }

        this.createAdminInStart();
        if(this.teamService.getAllTeams() == 0) {
            this.createAllTeams();
        }




    }

    private void createAllTeams() {
        this.createTeam("team1",1);
        this.createTeam("team2",1);
        this.createTeam("team3",3);
        this.createTeam("team4",2);
        this.createTeam("team5",3);
        this.createTeam("team6",3);
        this.createTeam("team7",3);
    }

    private void createRoleUser() {
        Role userRole = new Role();
        userRole.setId(1);
        userRole.setAuthority("ROLE_USER");
        this.roleService.save(userRole);
    }

    private void createRoleAdmin() {
        Role adminRole = new Role();
        adminRole.setId(2);
        adminRole.setAuthority("ROLE_ADMIN");
        this.roleService.save(adminRole);
    }

    private void createNeededEmployers(){
        NeededEmployer neededEmployer = new NeededEmployer();
        neededEmployer.setId(1);
        neededEmployer.setMaxEmployers(100);
        neededEmployer.setCurrentEmployers(1);
        this.neededEmployersService.save(neededEmployer);
    }

    private void createAdminInStart() {
        List<Employee> allEmployers = this.adminService.getAllEmployers();
        if(allEmployers.size() == 0) {
            RegisterBindingModel model = new RegisterBindingModel();
            model.setUsername("1111122222");
            model.setPassword("123");
            model.setFirstName("admin");
            model.setLastName("admin");
            model.setSalary(20);
            this.adminService.register(model);
        }
    }

    private void createTeam(String name,int constant){
        Team team = new Team();
        team.setTeamName(name);
        team.setResidue(constant);
        this.teamService.create(team);
    }

}
