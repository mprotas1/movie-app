package com.protas.movieapp.utils.loader;

import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.model.RoomSize;

import java.util.List;

public interface SeatLoader {
    List<Seat> loadSeats();
    List<Seat> loadSeats(RoomSize roomSize);
}
