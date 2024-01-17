package com.protas.movieapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record SeatDTO(
        @Size(max = 100) @NotNull @Positive
        Integer row,
        @Size(max = 100) @NotNull @Positive
        Integer column
) {}
