package com.protas.movieapp.entity;

import com.protas.movieapp.model.RoleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role withRoleType(RoleType roleType) {
        this.roleType = roleType;
        return this;
    }
}
