package com.protas.movieapp.utils.loader;

import com.protas.movieapp.model.SeatType;

public record SeatData(
    Integer rowNumber,
    Integer columnNumber,
    SeatType seatType
) {}

