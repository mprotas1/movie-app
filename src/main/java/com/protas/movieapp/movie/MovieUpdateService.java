package com.protas.movieapp.movie;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class MovieUpdateService {
    private MovieReadService movieReadService;
    private MovieRepository movieRepository;
    public void update(MovieDTO movieDTO, Long id) {
        Movie movieToUpdate = movieReadService.findById(id);
        Movie updated = updateMovieUsingDTO(movieToUpdate, movieDTO);
        movieRepository.save(updated);
    }

    private Movie updateMovieUsingDTO(Movie movie, MovieDTO movieDTO) {
        movie.setDescription(movieDTO.description());
        movie.setTitle(movieDTO.title());
        return movie;
    }
}
