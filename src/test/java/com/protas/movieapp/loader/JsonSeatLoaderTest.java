package com.protas.movieapp.loader;

import com.protas.movieapp.model.RoomSize;
import com.protas.movieapp.utils.loader.AbstractSeatLoader;
import com.protas.movieapp.utils.loader.json.JsonSeatLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonSeatLoaderTest {
    AbstractSeatLoader seatLoader;

    @BeforeEach
    void setUp() {
        seatLoader = new JsonSeatLoader();
    }

    @Test
    void shouldHaveSmallSizeByDefault() {
        var check = seatLoader.getRoomSize();
        assertEquals(RoomSize.SMALL, check);
    }

    @Test
    void shouldLoadSmallScreeningRoom() {
        var seats = seatLoader.loadSeats();
        assertNotNull(seats);
        assertFalse(seats.isEmpty());
    }

    @Test
    void shouldLoadMediumScreeningRoom() {
        seatLoader.setRoomSize(RoomSize.MEDIUM);
        var seats = seatLoader.loadSeats();
        assertNotNull(seats);
        assertFalse(seats.isEmpty());
        assertEquals(RoomSize.MEDIUM, seatLoader.getRoomSize());
    }

    @Test
    void shouldLoadBigScreeningRoom() {
        seatLoader.setRoomSize(RoomSize.BIG);
        var seats = seatLoader.loadSeats();
        assertNotNull(seats);
        assertFalse(seats.isEmpty());
        assertEquals(RoomSize.BIG, seatLoader.getRoomSize());
    }

    @Test
    void shouldLoadAllScreeningRoomSeats() {
        var seats = seatLoader.loadSeats();
        assertFalse(seats.isEmpty());
        assertEquals(seats.size(), 50);
    }

}
