package com.protas.movieapp.user;

import com.protas.movieapp.utils.jwt.JwtUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserAuthService.class.getName());
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthenticationResponse register(RegisterRequestDTO request) {
        User savedUser = userRepository.save(userMapper.registerRequestToUserEntity(request));
        LOGGER.info("Registered user: {} with id: {}", savedUser.getEmail(), savedUser.getId());
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

        User user = (User) userService.findByEmail(request.email());

        String token = jwtUtils.generateToken(user);
        LOGGER.info("Authenticated user: {} with id: {}", user.getEmail(), user.getId());
        return new AuthenticationResponse(token);
    }

    public User getUserFromAuthentication(Authentication authentication) {
        Principal principal = (Principal) authentication.getPrincipal();
        return userRepository.findByEmail(principal.getName()).orElseThrow(EntityNotFoundException::new);
    }

}
