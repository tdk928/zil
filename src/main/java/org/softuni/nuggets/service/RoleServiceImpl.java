package org.softuni.nuggets.service;

import org.softuni.nuggets.entities.Role;
import org.softuni.nuggets.repositories.role.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean save(Role role){
        this.roleRepository.save(role);
        return true;

    }

    @Override
    public boolean findByAuthority(String authority) {
        Role role = this.roleRepository.findByAuthority(authority);
        return role != null;
    }

    @Override
    public Role findById(Long id) {
        return this.roleRepository.findById(id);
    }
}
