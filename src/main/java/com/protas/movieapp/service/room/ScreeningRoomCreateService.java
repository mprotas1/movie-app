package com.protas.movieapp.service.room;

import com.protas.movieapp.dto.ScreeningRoomDTO;
import com.protas.movieapp.entity.address.Address;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.entity.cinema.ScreeningRoom;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.exception.EntityAlreadyExistsException;
import com.protas.movieapp.repository.CinemaRepository;
import com.protas.movieapp.repository.ScreeningRoomRepository;
import com.protas.movieapp.service.cinema.CinemaReadService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ScreeningRoomCreateService {
    private final Logger logger = LoggerFactory.getLogger(ScreeningRoomCreateService.class.getName());
    private final ScreeningRoomRepository repository;
    private final CinemaReadService cinemaReadService;

    public ScreeningRoom createRoomInCinema(ScreeningRoomDTO dto, Long cinemaId) {
        Cinema cinema = cinemaReadService.findById(cinemaId);
        if(screeningRoomAlreadyExists(dto, cinemaId)) {
            logger.error("The room with number {} already exists in the cinema", dto.screeningRoomNumber());
            throw new EntityAlreadyExistsException("The room with " + dto.screeningRoomNumber() + " number already exists in this cinema");
        }
        return createRoomAndAddToCinema(dto, cinema);
    }

    public ScreeningRoom createRoomInCinema(ScreeningRoomDTO dto, Cinema cinema) {
        return createRoomInCinema(dto, cinema.getId());
    }

    private ScreeningRoom createRoomAndAddToCinema(ScreeningRoomDTO dto, Cinema cinema) {
        ScreeningRoom room = new ScreeningRoom(dto.screeningRoomNumber(), cinema);
        cinema.addScreeningRoom(room);
        return repository.save(room);
    }

    private boolean screeningRoomAlreadyExists(ScreeningRoomDTO dto, Long cinemaId) {
        return repository.findByScreeningRoomNumberAndCinemaId(dto.screeningRoomNumber(), cinemaId).isPresent();
    }
}
