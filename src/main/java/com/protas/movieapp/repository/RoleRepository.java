package com.protas.movieapp.repository;

import com.protas.movieapp.entity.Role;
import com.protas.movieapp.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
