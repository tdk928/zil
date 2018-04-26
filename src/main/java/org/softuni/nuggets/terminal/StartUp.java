package org.softuni.nuggets.terminal;

import org.softuni.nuggets.entities.Role;
import org.softuni.nuggets.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartUp implements CommandLineRunner{
    private RoleService roleService;

    public StartUp(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(!this.roleService.findByAuthority("ROLE_USER")) {
            Role userRole = new Role();
            userRole.setId(1);
            userRole.setAuthority("ROLE_USER");
            this.roleService.save(userRole);
        }
        if(!this.roleService.findByAuthority("ROLE_ADMIN")) {
            Role adminRole = new Role();
            adminRole.setId(2);
            adminRole.setAuthority("ROLE_ADMIN");
            this.roleService.save(adminRole);
        }
    }
}
