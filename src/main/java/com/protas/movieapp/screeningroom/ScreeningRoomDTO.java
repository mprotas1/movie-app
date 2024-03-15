package com.protas.movieapp.screeningroom;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;

public record ScreeningRoomDTO(
        Integer screeningRoomNumber,
        RoomSize size
) {}
