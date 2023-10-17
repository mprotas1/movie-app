package com.protas.movieapp.loader;

import com.protas.movieapp.model.RoomSize;
import com.protas.movieapp.utils.loader.AbstractSeatLoader;
import com.protas.movieapp.utils.loader.JsonSeatLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonSeatLoaderTest {
    String filePath;
    AbstractSeatLoader seatLoader;

    @BeforeEach
    void setUp() {
        filePath = "src/main/resources/data/";
        seatLoader = new JsonSeatLoader();
    }

    @Test
    void shouldHaveSmallSizeDefault() {
        var check = seatLoader.getRoomSize();
        assertEquals(RoomSize.SMALL, check);
    }

    @Test
    void shouldLoadSmallScreeningRoom() {
        var seats = seatLoader.loadSeats();
        assertNotNull(seats);
    }

    @Test
    void shouldLoadMediumScreeningRoom() {
        seatLoader = new JsonSeatLoader().withRoomSize(RoomSize.MEDIUM);
        var seats = seatLoader.loadSeats();
        assertNotNull(seats);
    }

}
