package com.protas.movieapp.cinema;

import com.protas.movieapp.config.CinemaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaReadService {
    private final CinemaRepository cinemaRepository;

    public List<Cinema> findAll(Pageable pageable) {
        return cinemaRepository.findAll(pageable).getContent();
    }

    public Cinema findById(Long id) {
        return cinemaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find the cinema with id: %d", id)));
    }

}
