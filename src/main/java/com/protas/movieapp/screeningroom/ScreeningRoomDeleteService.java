package com.protas.movieapp.screeningroom;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ScreeningRoomDeleteService {
    private final Logger LOGGER = LoggerFactory.getLogger(ScreeningRoomDeleteService.class.getName());
    private final ScreeningRoomRepository repository;

    public void deleteRoom(Integer roomId, Long cinemaId) {
        LOGGER.info("Deleting the room with id: {} from cinema with id: {}", roomId, cinemaId);
        repository.deleteByIdAndCinemaId(Long.valueOf(roomId), cinemaId);
    }

}
