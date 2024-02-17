package com.protas.movieapp.service.room;

import com.protas.movieapp.dto.ScreeningRoomDTO;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.entity.cinema.ScreeningRoom;
import com.protas.movieapp.exception.EntityAlreadyExistsException;
import com.protas.movieapp.repository.ScreeningRoomRepository;
import com.protas.movieapp.service.cinema.CinemaReadService;
import com.protas.movieapp.service.seat.SeatCreateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScreeningRoomCreateService {
    private final Logger logger = LoggerFactory.getLogger(ScreeningRoomCreateService.class.getName());
    private final ScreeningRoomRepository repository;
    private final CinemaReadService cinemaReadService;
    private final SeatCreateService seatCreateService;

    public ScreeningRoom createRoomInCinema(ScreeningRoomDTO dto, Long cinemaId) {
        Cinema cinema = cinemaReadService.findById(cinemaId);
        if(screeningRoomAlreadyExists(dto, cinemaId)) {
            logger.error("The room with number {} already exists in the cinema", dto.screeningRoomNumber());
            throw new EntityAlreadyExistsException("The room with " + dto.screeningRoomNumber() + " number already exists in this cinema");
        }
        return createRoomAndAddToCinema(dto, cinema);
    }

    private ScreeningRoom createRoomAndAddToCinema(ScreeningRoomDTO dto, Cinema cinema) {
        ScreeningRoom room = new ScreeningRoom(dto, cinema);
        setRoomSeats(room);
        return repository.save(room);
    }

    private void setRoomSeats(ScreeningRoom room) {
        seatCreateService.createSeats(room.getRoomSize()).forEach(seat -> seat.setRoom(room));
    }

    private boolean screeningRoomAlreadyExists(ScreeningRoomDTO dto, Long cinemaId) {
        return repository
                .findByScreeningRoomNumberAndCinemaId(dto.screeningRoomNumber(), cinemaId)
                .isPresent();
    }
}
