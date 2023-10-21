package com.protas.movieapp.service.seat;

import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatReadService {
    private final SeatRepository repository;

    public List<Seat> getAllRoomSeats(Long roomId) {
        //return repository.findAllByRoomId(roomId);
        return Collections.emptyList();
    }
}
