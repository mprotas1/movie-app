package com.protas.movieapp.service.service;

import com.protas.movieapp.entity.movie.Screening;
import com.protas.movieapp.repository.ScreeningRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreeningReadService {
    private final ScreeningRepository screeningRepository;

    public Screening findById(Long id) {
        return screeningRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
