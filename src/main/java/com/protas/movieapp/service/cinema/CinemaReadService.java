package com.protas.movieapp.service.cinema;

import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.repository.CinemaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaReadService {
    private final CinemaRepository cinemaRepository;

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public Cinema findById(Integer id) {
        return cinemaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
