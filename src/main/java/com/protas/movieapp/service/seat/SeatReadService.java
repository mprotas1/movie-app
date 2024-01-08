package com.protas.movieapp.service.seat;

import com.protas.movieapp.dto.SeatDTO;
import com.protas.movieapp.entity.cinema.ScreeningRoom;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.entity.reservation.Reservation;
import com.protas.movieapp.repository.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatReadService {
    private final SeatRepository repository;

    public List<Seat> getAllRoomSeats(Long roomId) {
        //return repository.findAllByRoomId(roomId);
        return Collections.emptyList();
    }

    public Seat getSeatFromScreeningRoomAndSeatDTO(ScreeningRoom screeningRoom, SeatDTO seatDTO) {
        return repository.findByScreeningRoomAndRowAndColumn(screeningRoom.getId(), seatDTO.row(), seatDTO.column())
                .orElseThrow(EntityNotFoundException::new);
    }
}
