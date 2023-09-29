package com.protas.movieapp.controller;

import com.protas.movieapp.dto.AuthenticationRequestDTO;
import com.protas.movieapp.dto.RegisterRequestDTO;
import com.protas.movieapp.model.AuthenticationResponse;
import com.protas.movieapp.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequestDTO request) {
        AuthenticationResponse authenticationResponse = userAuthService.register(request);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequestDTO request) {
        AuthenticationResponse authenticationResponse = userAuthService.authenticate(request);
        return ResponseEntity.ok(authenticationResponse);
    }

}
