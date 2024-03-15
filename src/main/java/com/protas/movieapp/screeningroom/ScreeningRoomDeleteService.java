package com.protas.movieapp.screeningroom;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ScreeningRoomDeleteService {
    private final Logger LOGGER = LoggerFactory.getLogger(ScreeningRoomDeleteService.class.getName());
    private final ScreeningRoomRepository repository;

    void deleteRoom(Long roomId) {
        LOGGER.info("Deleting the room with id: {}", roomId);
        repository.deleteById(roomId);
    }
}
