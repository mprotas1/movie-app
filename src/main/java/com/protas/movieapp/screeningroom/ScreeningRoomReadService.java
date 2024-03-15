package com.protas.movieapp.screeningroom;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningRoomReadService {
    private final ScreeningRoomRepository repository;
    private final ScreeningRoomMapper mapper;

    public List<ScreeningRoomDTO> findAllByCinemaId(Long cinemaId) {
        return repository.findByCinemaId(cinemaId)
                .orElseThrow(EntityNotFoundException::new)
                .stream()
                .map(mapper::fromEntity)
                .toList();
    }

    public ScreeningRoom findById(Long roomId) {
        return repository.findById(roomId)
                .orElseThrow(EntityNotFoundException::new);
    }

    boolean screeningRoomAlreadyExists(ScreeningRoomDTO dto, Long cinemaId) {
        return repository
                .findByScreeningRoomNumberAndCinemaId(dto.screeningRoomNumber(), cinemaId)
                .isPresent();
    }
}
