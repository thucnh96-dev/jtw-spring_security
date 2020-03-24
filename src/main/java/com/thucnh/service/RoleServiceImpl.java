package com.thucnh.service;

import com.thucnh.model.Role;
import com.thucnh.model.enums.ROLE;
import com.thucnh.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(ROLE role) {
        return roleRepository.findByName(role);
    }
}
