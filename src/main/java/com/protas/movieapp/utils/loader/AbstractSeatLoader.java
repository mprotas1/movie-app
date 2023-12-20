package com.protas.movieapp.utils.loader;

import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.model.RoomSize;
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

    public AbstractSeatLoader(RoomSize roomSize) {
        this.roomSize = roomSize;
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
