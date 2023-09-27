package com.protas.movieapp.model;

public enum RoleType {
    USER,
    EMPLOYEE,
    MODERATOR,
    ADMINISTRATOR;

    public final String roleName;

    RoleType() {
        roleName = "ROLE_" + this.name();
    }
}
