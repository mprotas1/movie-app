package com.protas.movieapp.controller;

import com.protas.movieapp.dto.AuthenticationRequestDTO;
import com.protas.movieapp.dto.RegisterRequestDTO;
import com.protas.movieapp.model.AuthenticationResponse;
import com.protas.movieapp.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class.getName());
    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequestDTO request) {
        AuthenticationResponse authenticationResponse = userAuthService.register(request);
        logger.info("Authentication response from /register: {}", authenticationResponse);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequestDTO request) {
        logger.info("Authentication request from /authenticate: {}", request);
        AuthenticationResponse authenticationResponse = userAuthService.authenticate(request);
        logger.info("Authentication response from /authenticate: {}", authenticationResponse);
        return ResponseEntity.ok(authenticationResponse);
    }

}
