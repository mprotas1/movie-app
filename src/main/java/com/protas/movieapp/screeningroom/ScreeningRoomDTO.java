package com.protas.movieapp.screeningroom;

import com.protas.movieapp.screeningroom.RoomSize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;

public record ScreeningRoomDTO(
        @Positive
        Integer screeningRoomNumber,
        @Enumerated(EnumType.STRING)
        RoomSize size
) {}
