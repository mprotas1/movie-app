package com.protas.movieapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(@NotBlank String email, @NotBlank String username, @NotBlank String password) { }
