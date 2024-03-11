package com.protas.movieapp.seat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;

public record SeatCreationDTO(@Size(max = 100) Integer row,
                              @Size(max = 100) Integer column,
                              @Enumerated(EnumType.STRING)
                              SeatType seatType
) {}
