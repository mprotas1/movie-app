package com.protas.movieapp.utils.loader.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.mapper.SeatMapper;
import com.protas.movieapp.model.RoomSize;
import com.protas.movieapp.utils.loader.AbstractSeatLoader;
import com.protas.movieapp.utils.loader.SeatData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class JsonSeatLoader extends AbstractSeatLoader {
    private final Logger logger = LoggerFactory.getLogger(JsonSeatLoader.class.getName());
    private final String PATH_PREFIX = "src/main/resources/data/";
    private final String ROOM_SMALL_PATH = "room-small.json";
    private final String ROOM_MEDIUM_PATH = "room-medium.json";
    private final String ROOM_BIG_PATH = "room-big.json";
    private final ObjectMapper mapper = new ObjectMapper();

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
        return readSeatsBySize(this.getRoomSize());
    }

    private List<SeatData> readSeatsBySize(RoomSize roomSize) {
        Path path = getPathFromRoomSize(roomSize);
        return fetchDataFromJsonFile(path);
    }

    private Path getPathFromRoomSize(RoomSize roomSize) {
        Path result;
        switch (roomSize) {
            case SMALL -> result =  Paths.get(PATH_PREFIX, ROOM_SMALL_PATH);
            case MEDIUM -> result =  Paths.get(PATH_PREFIX, ROOM_MEDIUM_PATH);
            case BIG -> result =  Paths.get(PATH_PREFIX, ROOM_BIG_PATH);
            default -> throw new IllegalArgumentException("Invalid room size: " + roomSize);
        }

        return result;
    }

    private List<SeatData> fetchDataFromJsonFile(Path path) {
        try {
            SeatData[] seatDataArray = mapper.readValue(path.toFile(), SeatData[].class);
            return Arrays.asList(seatDataArray);
        } catch (IOException e) {
            logger.error("An error occured during reading data from JSON file");
            return Collections.emptyList();
        }

    }
}
