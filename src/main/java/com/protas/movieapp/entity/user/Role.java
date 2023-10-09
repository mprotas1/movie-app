package com.protas.movieapp.entity.user;

import com.protas.movieapp.model.RoleType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    private User user;

    public Role withRoleType(RoleType roleType) {
        this.roleType = roleType;
        return this;
    }
}
