package com.protas.movieapp.dto;

import jakarta.validation.constraints.Positive;

public record ScreeningRoomDTO(
        @Positive
        Integer screeningRoomNumber
) {}
