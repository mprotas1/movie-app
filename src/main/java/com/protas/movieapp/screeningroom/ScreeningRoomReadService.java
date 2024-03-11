package com.protas.movieapp.screeningroom;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningRoomReadService {
    private final ScreeningRoomRepository repository;
    public List<ScreeningRoom> findAllByCinemaId(Long cinemaId) {
        return repository.findByCinemaId(cinemaId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public ScreeningRoom findById(Integer roomId) {
        return repository.findById(roomId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
