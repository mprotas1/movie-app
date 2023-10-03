package com.protas.movieapp.service;

import com.protas.movieapp.dto.AuthenticationRequestDTO;
import com.protas.movieapp.dto.RegisterRequestDTO;
import com.protas.movieapp.entity.User;
import com.protas.movieapp.entity.Role;
import com.protas.movieapp.model.AuthenticationResponse;
import com.protas.movieapp.model.RoleType;
import com.protas.movieapp.repository.UserRepository;
import com.protas.movieapp.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final Logger logger = LoggerFactory.getLogger(UserAuthService.class.getName());
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequestDTO request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.builder()
                .email(request.email())
                .username(request.username())
                .password(encodedPassword)
                .build();

        assignToUserRole(user);

        User savedUser = userRepository.save(user);
        logger.info("Successfully register user: {} with id: {}", savedUser.getEmail(), savedUser.getId());

        String token = jwtUtils.generateToken(savedUser);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository
                .findByEmail(request.email()).orElseThrow(
                        () -> new UsernameNotFoundException(String.format("Could not find the user with email: %s", request.email()))
                );
        String token = jwtUtils.generateToken(user);
        logger.info("Successfully authenticated user: {} with id: {}", request.email(), user.getId());
        return new AuthenticationResponse(token);
    }

    private void assignToUserRole(User user) {
        Role userRole = new Role();
        userRole.setRoleType(RoleType.USER);

        if(user.getRoles() == null) {
            user.setRoles(Set.of(userRole));
            return;
        }

        user.getRoles().add(userRole);
    }
}
