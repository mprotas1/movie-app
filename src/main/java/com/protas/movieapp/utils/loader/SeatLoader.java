package com.protas.movieapp.utils.loader;

import com.protas.movieapp.seat.Seat;
import com.protas.movieapp.screeningroom.RoomSize;

import java.util.List;

public interface SeatLoader {
    List<Seat> loadSeats();
    List<Seat> loadSeats(RoomSize roomSize);
}
