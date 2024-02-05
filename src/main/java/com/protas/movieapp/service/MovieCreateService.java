package com.protas.movieapp.service;

import com.protas.movieapp.dto.MovieDTO;
import com.protas.movieapp.entity.movie.Director;
import com.protas.movieapp.entity.movie.Movie;
import com.protas.movieapp.repository.MovieRepository;
import com.protas.movieapp.service.director.DirectorReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCreateService {
    private final MovieRepository movieRepository;
    private final DirectorReadService directorReadService;

    public Movie create(MovieDTO dto) {
        return movieRepository.save(setUpMovieFromDto(dto));
    }

    private Movie setUpMovieFromDto(MovieDTO dto) {
        Movie movie = new Movie();
        Director director = directorReadService.findDirectorById(dto.directorId());
        movie.setDirector(director);
        movie.setDescription(dto.description());
        movie.setDurationInMinutes(dto.durationInMinutes());

        return movie;
    }
}
