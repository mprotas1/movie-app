package com.protas.movieapp.utils.loader.factory;

import com.protas.movieapp.model.RoomSize;
import com.protas.movieapp.utils.loader.AbstractSeatLoader;
import com.protas.movieapp.utils.loader.json.JsonSeatLoader;

public class SeatLoaderFactory {
    public static AbstractSeatLoader jsonSeatLoader(RoomSize roomSize) {
        return new JsonSeatLoader().withRoomSize(roomSize);
    }

}
