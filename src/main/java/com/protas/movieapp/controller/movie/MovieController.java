package com.protas.movieapp.controller.movie;

import com.protas.movieapp.entity.movie.Movie;
import com.protas.movieapp.service.movie.MovieReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieReadService readService;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        // TODO
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        return ResponseEntity.ok(readService.findById(id));
    }

}
