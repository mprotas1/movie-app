package com.protas.movieapp.utils.loader;

import com.protas.movieapp.seat.Seat;
import com.protas.movieapp.screeningroom.RoomSize;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public abstract class AbstractSeatLoader implements SeatLoader {
    private RoomSize roomSize;

    public AbstractSeatLoader() {
        this.roomSize = RoomSize.SMALL;
    }

    @Override
    public List<Seat> loadSeats() {
        return Collections.emptyList();
    }

    @Override
    public List<Seat> loadSeats(RoomSize roomSize) {
        return Collections.emptyList();
    }

    public AbstractSeatLoader withRoomSize(RoomSize roomSize) {
        this.roomSize = roomSize;
        return this;
    }
}
