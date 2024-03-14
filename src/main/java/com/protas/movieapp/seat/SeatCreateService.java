package com.protas.movieapp.seat;

import com.protas.movieapp.exception.EntityAlreadyExistsException;
import com.protas.movieapp.screeningroom.RoomSize;
import com.protas.movieapp.screeningroom.ScreeningRoomReadService;
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
        var room = screeningRoomReadService.findById(screeningRoomId);
        return seatRepository.save(Seat.builder()
                .withRow(dto.row())
                .withColumn(dto.column())
                .withSeatType(seatType)
                .withScreeningRoom(room)
                .build()
        );
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
