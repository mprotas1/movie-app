package com.protas.movieapp.user;

import java.util.Collection;
import java.util.Set;

public class UserBuilder {
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRoles(Collection<Role> roles) {
        this.roles = (Set<Role>) roles;
        return this;
    }

    public User build() {
        User result = new User();
        result.setUsername(username);
        result.setPassword(password);
        result.setEmail(email);
        result.addRole(roles);

        return result;
    }

}
