package com.protas.movieapp.director;

import jakarta.validation.constraints.NotBlank;

public record DirectorDTO(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName
) {}
