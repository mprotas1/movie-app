package com.protas.movieapp.controller.movie;

import com.protas.movieapp.dto.MovieDTO;
import com.protas.movieapp.entity.movie.Movie;
import com.protas.movieapp.service.MovieCreateService;
import com.protas.movieapp.service.movie.MovieReadService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieReadService readService;
    private final MovieCreateService movieCreateService;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll(@PageableDefault(value = Integer.MAX_VALUE)
                                                   Pageable pageable) {
        return ResponseEntity.ok(readService.findMovies(pageable.getPageNumber(),
                pageable.getPageSize())
                .getContent());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        return ResponseEntity.ok(readService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody MovieDTO dto) {
        return ResponseEntity.ok(movieCreateService.create(dto));
    }

}
