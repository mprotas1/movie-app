package com.protas.movieapp.movie;

import com.protas.movieapp.director.Director;
import com.protas.movieapp.director.DirectorReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MovieCreateService {
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
