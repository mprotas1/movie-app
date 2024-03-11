package com.protas.movieapp.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(@NotBlank String email, @NotBlank String username, @NotBlank String password) { }
