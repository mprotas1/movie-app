package com.protas.movieapp.service.seat;

import com.protas.movieapp.dto.SeatDTO;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.exception.EntityAlreadyExistsException;
import com.protas.movieapp.model.RoomSize;
import com.protas.movieapp.model.SeatType;
import com.protas.movieapp.repository.SeatRepository;
import com.protas.movieapp.service.room.ScreeningRoomReadService;
import com.protas.movieapp.utils.loader.json.JsonSeatLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatCreateService {
    private final JsonSeatLoader loader;
    private final SeatRepository seatRepository;
    private final ScreeningRoomReadService screeningRoomReadService;
    private final SeatReadService seatReadService;

    public List<Seat> createSeats(RoomSize roomSize) {
        return seatRepository.saveAll(loader.loadSeats(roomSize));
    }

    public Seat createSeat(SeatDTO dto, SeatType seatType, Long screeningRoomId) {
        throwExceptionOnExistingSeat(dto, screeningRoomId);
        var room = screeningRoomReadService.findById(Math.toIntExact(screeningRoomId));
        var seat = new Seat();
        seat.setRoom(room);
        seat.setSeatRowNumber(dto.row());
        seat.setSeatColumnNumber(dto.column());
        seat.setSeatType(seatType);
        return seatRepository.save(seat);
    }

    private void throwExceptionOnExistingSeat(SeatDTO dto, Long screeningRoomId) {
        if(seatReadService.seatAlreadyExists(dto, screeningRoomId)) {
            String message = String.format("Seat with row: %d and column: %d already exists in screening room with id: %d",
                    dto.row(),
                    dto.column(),
                    screeningRoomId);
            throw new EntityAlreadyExistsException(message);
        }
    }
}
