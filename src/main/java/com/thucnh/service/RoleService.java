package com.thucnh.service;

import com.thucnh.model.Role;
import com.thucnh.model.enums.ROLE;

public interface RoleService {
    Role findByName(ROLE role);
}
