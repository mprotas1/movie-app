package com.protas.movieapp.dto;

import com.protas.movieapp.model.RoomSize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;

public record ScreeningRoomDTO(
        @Positive
        Integer screeningRoomNumber,
        @Enumerated(EnumType.STRING)
        RoomSize size
) {}
