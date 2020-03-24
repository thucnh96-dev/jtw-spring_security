package com.thucnh.model;

import com.thucnh.model.enums.ROLE;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_ROLE")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name",unique = true)
    private ROLE name;

    public Role() {
    }
}
