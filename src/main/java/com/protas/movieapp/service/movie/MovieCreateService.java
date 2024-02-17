package com.protas.movieapp.service.movie;

import com.protas.movieapp.entity.movie.Movie;
import com.protas.movieapp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCreateService {
    private MovieRepository movieRepository;

    public Movie create() {
        return null;
    }
}
