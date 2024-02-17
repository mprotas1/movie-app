package com.protas.movieapp.service.seat;

import com.protas.movieapp.dto.SeatDTO;
import com.protas.movieapp.entity.cinema.ScreeningRoom;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.repository.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatReadService {
    private final SeatRepository repository;

    public List<Seat> getAllRoomSeats(Long roomId) {
        return repository.findAllByRoomId(roomId);
    }

    public Seat getSeatFromScreeningRoomAndSeatDTO(ScreeningRoom screeningRoom, SeatDTO seatDTO) {
        return repository.findByRoomIdAndSeatRowNumberAndSeatColumnNumber(screeningRoom.getId(), seatDTO.row(), seatDTO.column())
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean seatAlreadyExists(SeatDTO dto, Long screeningRoomId) {
        // TODO: maybe replace it with DB validation instead of Stream API?
        return getAllRoomSeats(screeningRoomId).stream().anyMatch(seat -> seat.equalsDTO(dto));
    }
}
