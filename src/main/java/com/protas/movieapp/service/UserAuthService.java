package com.protas.movieapp.service;

import com.protas.movieapp.dto.AuthenticationRequestDTO;
import com.protas.movieapp.dto.RegisterRequestDTO;
import com.protas.movieapp.entity.User;
import com.protas.movieapp.entity.Role;
import com.protas.movieapp.model.AuthenticationResponse;
import com.protas.movieapp.model.RoleType;
import com.protas.movieapp.repository.UserRepository;
import com.protas.movieapp.utils.JwtUtils;
import jakarta.persistence.EntityNotFoundException;
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
public class UserAuthService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UserAuthService.class.getName());
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

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

        User user = (User) loadUserByUsername(request.email());
        String token = jwtUtils.generateToken(user);
        logger.info("Successfully authenticated user: {} with id: {}", request.email(), user.getId());
        return new AuthenticationResponse(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("I'm loading UserDetails by email: {}", username);
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Could not find the appropriate entity with email: " + username));
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
