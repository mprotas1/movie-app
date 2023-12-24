package com.protas.movieapp.service.seat;

import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.model.RoomSize;
import com.protas.movieapp.repository.SeatRepository;
import com.protas.movieapp.utils.loader.json.JsonSeatLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatCreateService {
    private final JsonSeatLoader loader;
    private final SeatRepository seatRepository;

    public List<Seat> createSeats(RoomSize roomSize) {
        List<Seat> seats = loader.loadSeats(roomSize);
        return seatRepository.saveAll(seats);
    }
}
