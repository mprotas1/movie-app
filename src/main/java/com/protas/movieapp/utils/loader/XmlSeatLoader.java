package com.protas.movieapp.utils.loader;

import com.protas.movieapp.seat.Seat;
import com.protas.movieapp.screeningroom.RoomSize;

import java.util.Collections;
import java.util.List;

public class XmlSeatLoader implements SeatLoader {
    @Override
    public List<Seat> loadSeats() {
        return Collections.emptyList();
    }

    @Override
    public List<Seat> loadSeats(RoomSize roomSize) {
        return Collections.emptyList();
    }
}
