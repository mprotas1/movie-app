package com.protas.movieapp.movie;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieReadService {
    private final MovieRepository movieRepository;

    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Page<Movie> findMovies(Integer page, Integer size) {
        return movieRepository.findAll(Pageable.ofSize(size)
                .withPage(page));
    }

}
