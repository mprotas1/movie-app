package com.protas.movieapp.mapper;

import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.utils.loader.SeatData;

public class SeatMapper {

    public static Seat fromJson(SeatData data) {
        Seat seat = new Seat();
        seat.setSeatColumnNumber(data.columnNumber());
        seat.setSeatRowNumber(data.rowNumber());
        seat.setSeatType(data.seatType());
        return seat;
    }

}
