package com.protas.movieapp.user;

import jakarta.validation.Valid;
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
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class.getName());
    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequestDTO request) {
        AuthenticationResponse authenticationResponse = userAuthService.register(request);
        LOGGER.info("Authentication response from /register: {}", authenticationResponse);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequestDTO request) {
        AuthenticationResponse authenticationResponse = userAuthService.authenticate(request);
        LOGGER.info("Authenticating ");
        return ResponseEntity.ok(authenticationResponse);
    }

}
