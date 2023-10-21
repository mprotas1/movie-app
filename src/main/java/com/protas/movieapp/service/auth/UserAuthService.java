package com.protas.movieapp.service.auth;

import com.protas.movieapp.dto.AuthenticationRequestDTO;
import com.protas.movieapp.dto.RegisterRequestDTO;
import com.protas.movieapp.entity.user.User;
import com.protas.movieapp.mapper.UserMapper;
import com.protas.movieapp.model.AuthenticationResponse;
import com.protas.movieapp.repository.UserRepository;
import com.protas.movieapp.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final Logger logger = LoggerFactory.getLogger(UserAuthService.class.getName());
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthenticationResponse register(RegisterRequestDTO request) {
        User savedUser = userRepository.save(userMapper.registerRequestToUserEntity(request));
        logger.info("Successfully registered user: {} with id: {}", savedUser.getEmail(), savedUser.getId());
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

        Optional<User> user = userRepository.findByEmail(request.email());

        String token = jwtUtils.generateToken(user.get());
        logger.info("Successfully authenticated user: {} with id: {}", request.email(), user.get().getId());
        return new AuthenticationResponse(token);
    }

}
