package com.protas.movieapp.dto;

import jakarta.validation.constraints.NotBlank;

public record DirectorDTO(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName
) {}
