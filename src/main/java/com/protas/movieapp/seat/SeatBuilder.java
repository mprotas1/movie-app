package com.protas.movieapp.seat;

import com.protas.movieapp.screeningroom.ScreeningRoom;

public class SeatBuilder {
    private Integer row;
    private Integer column;
    private ScreeningRoom screeningRoom;
    private SeatType seatType;

    public SeatBuilder withRow(Integer row) {
        this.row = row;
        return this;
    }

    public SeatBuilder withColumn(Integer column) {
        this.column = column;
        return this;
    }

    public SeatBuilder withScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
        return this;
    }

    public SeatBuilder withSeatType(SeatType seatType) {
        this.seatType = seatType;
        return this;
    }

    public Seat build() {
        Seat seat = new Seat();
        seat.setSeatRowNumber(row);
        seat.setSeatColumnNumber(column);
        seat.setRoom(screeningRoom);
        seat.setSeatType(seatType);
        return seat;
    }
}
