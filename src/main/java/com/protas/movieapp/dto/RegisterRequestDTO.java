package com.protas.movieapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(@NotEmpty String email, @NotEmpty String username, @NotEmpty @NotNull String password) { }
