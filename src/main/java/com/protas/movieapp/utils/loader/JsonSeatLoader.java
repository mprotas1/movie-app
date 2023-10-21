package com.protas.movieapp.utils.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.mapper.SeatMapper;
import com.protas.movieapp.model.RoomSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class JsonSeatLoader extends AbstractSeatLoader {
    private final String PATH_PREFIX = "src/main/resources/data/";
    private ObjectMapper mapper;

    public JsonSeatLoader() {
        super();
        mapper = new ObjectMapper();
    }

    @Override
    public List<Seat> loadSeats() {
        return readSeatsBySize().stream()
                .map(SeatMapper::fromJson)
                .toList();
    }

    @Override
    public List<Seat> loadSeats(RoomSize roomSize) {
        return readSeatsBySize(roomSize).stream()
                .map(SeatMapper::fromJson)
                .toList();
    }

    private List<SeatData> readSeatsBySize() {
        String path = getPathFromRoomSize(this.getRoomSize());
        return fetchDataFromJsonFile(path);
    }

    private List<SeatData> readSeatsBySize(RoomSize roomSize) {
        String path = getPathFromRoomSize(roomSize);
        return fetchDataFromJsonFile(path);
    }

    private String getPathFromRoomSize(RoomSize roomSize) {
        String result = "";
        switch (roomSize) {
            case SMALL -> result = PATH_PREFIX + "room-small.json";
            case MEDIUM -> result = PATH_PREFIX + "room-medium.json";
            case BIG -> result = PATH_PREFIX + "room-big.json";
        }

        return result;
    }

    private List<SeatData> fetchDataFromJsonFile(String path) {
        try {
            SeatData[] seatDataArray = mapper.readValue(new File(path), SeatData[].class);
            return Arrays.asList(seatDataArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
