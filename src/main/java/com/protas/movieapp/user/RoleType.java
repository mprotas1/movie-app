package com.protas.movieapp.user;

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
