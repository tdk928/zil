package org.softuni.nuggets.service;

import org.softuni.nuggets.entities.Role;

public interface RoleService {
    boolean save(Role role);

    boolean findByAuthority(String name);

    Role findById(Long id);
}
