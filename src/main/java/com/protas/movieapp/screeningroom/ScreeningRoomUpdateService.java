package com.protas.movieapp.screeningroom;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ScreeningRoomUpdateService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final ScreeningRoomRepository repository;
    private final ScreeningRoomMapper mapper;

    private ScreeningRoomDTO update(ScreeningRoomDTO dto, Long roomId) {
        LOGGER.info("Updating the ScreeningRoom with id: {}", roomId);
        var result = repository.findById(roomId).orElseThrow(EntityNotFoundException::new);
        result.updateFromDTO(dto);
        LOGGER.info("Updated Screening Room with id: {}\n{}", roomId, result);
        return mapper.fromEntity(repository.save(result));
    }
}
