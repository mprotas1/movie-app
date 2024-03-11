package com.protas.movieapp.exception;

import lombok.RequiredArgsConstructor;

import javax.security.sasl.AuthenticationException;

@RequiredArgsConstructor
public class UserAuthenticationException extends AuthenticationException {
    public UserAuthenticationException(String detail) {
        super(detail);
    }
}
