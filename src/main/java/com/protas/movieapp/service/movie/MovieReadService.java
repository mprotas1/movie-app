package com.protas.movieapp.service.movie;

import com.protas.movieapp.entity.movie.Movie;
import com.protas.movieapp.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieReadService {
    private final MovieRepository movieRepository;

    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

}
