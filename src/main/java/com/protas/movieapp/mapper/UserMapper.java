package com.protas.movieapp.mapper;

import com.protas.movieapp.dto.RegisterRequestDTO;
import com.protas.movieapp.entity.Role;
import com.protas.movieapp.entity.User;
import com.protas.movieapp.model.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User registerRequestToUserEntity(RegisterRequestDTO registerRequest, List<Role> roles) {
        User user = registerRequestToUserEntity(registerRequest);
        user.setRoles(roles);
        return user;
    }

    public User registerRequestToUserEntity(RegisterRequestDTO registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.email());
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRoles(new Role().withRoleType(RoleType.USER));
        return user;
    }
}
