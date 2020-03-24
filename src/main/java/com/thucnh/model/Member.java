package com.thucnh.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TB_USER")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addMemberRole(Role role){
        this.roles.add(role);
    }
    public void addMemberRoles(Set<Role> roles){
        this.roles.addAll(roles);
    }
}
