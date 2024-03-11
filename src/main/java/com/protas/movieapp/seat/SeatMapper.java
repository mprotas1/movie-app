package com.protas.movieapp.seat;

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
