package com.protas.movieapp.service.room;

import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.entity.cinema.ScreeningRoom;
import com.protas.movieapp.repository.ScreeningRoomRepository;
import com.protas.movieapp.service.cinema.CinemaReadService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningRoomDeleteService {
    private final Logger logger = LoggerFactory.getLogger(ScreeningRoomDeleteService.class.getName());
    private final ScreeningRoomRepository repository;
    private final CinemaReadService cinemaReadService;

    @Transactional
    public void deleteRoom(Integer roomId, Long cinemaId) {
        logger.info("Deleting the room with id: {} from cinema with id: {}", roomId, cinemaId);
        ScreeningRoom room = cinemaReadService.findById(cinemaId).getScreeningRooms()
                .stream()
                .filter(sr -> sr.getId().equals(roomId)).findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Could not find the room with id: " + roomId));
        Cinema cinema = room.getCinema();
        cinema.getScreeningRooms().remove(room);
        repository.delete(room);
    }

}
