package com.protas.movieapp.service.screening;

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

    public Screening findByMovieId(Long movieId) {
        return screeningRepository.findByContextMovieId(movieId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
