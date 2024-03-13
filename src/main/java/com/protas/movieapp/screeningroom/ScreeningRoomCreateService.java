package com.protas.movieapp.screeningroom;

import com.protas.movieapp.cinema.Cinema;
import com.protas.movieapp.cinema.CinemaMapper;
import com.protas.movieapp.exception.EntityAlreadyExistsException;
import com.protas.movieapp.cinema.CinemaReadService;
import com.protas.movieapp.seat.Seat;
import com.protas.movieapp.seat.SeatCreateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
public class ScreeningRoomCreateService {
    private final Logger LOGGER = LoggerFactory.getLogger(ScreeningRoomCreateService.class.getName());
    private final ScreeningRoomRepository repository;
    private final CinemaReadService cinemaReadService;
    private final ScreeningRoomReadService screeningRoomReadService;
    private final SeatCreateService seatCreateService;
    private final ScreeningRoomMapper screeningRoomMapper;

    public ScreeningRoomDTO createRoomInCinema(ScreeningRoomDTO dto, Long cinemaId) {
        Cinema cinema = cinemaReadService.findById(cinemaId);
        if(screeningRoomReadService.screeningRoomAlreadyExists(dto, cinemaId)) {
            LOGGER.error("The room with number {} already exists in the cinema", dto.screeningRoomNumber());
            throw new EntityAlreadyExistsException("The room with " + dto.screeningRoomNumber() + " number already exists in this cinema");
        }
        ScreeningRoom createdRoom = createRoomAndAddToCinema(dto, cinema);
        LOGGER.info("Created ScreeningRoom of size {} in the cinema with id: {}", createdRoom.getRoomSize(), cinemaId);
        return screeningRoomMapper.fromEntity(createdRoom);
    }

    private ScreeningRoom createRoomAndAddToCinema(ScreeningRoomDTO dto, Cinema cinema) {
        ScreeningRoom room = new ScreeningRoom(dto, cinema);
        setRoomSeats(room);
        return repository.save(room);
    }

    private void setRoomSeats(ScreeningRoom room) {
        seatCreateService.createSeats(room.getRoomSize()).forEach(seat -> seat.setRoom(room));
    }
}
