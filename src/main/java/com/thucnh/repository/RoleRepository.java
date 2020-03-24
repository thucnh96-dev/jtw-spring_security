package com.thucnh.repository;

import com.thucnh.model.Role;
import com.thucnh.model.enums.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(ROLE role);
}
