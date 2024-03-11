package com.protas.movieapp.movie;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
@RequiredArgsConstructor
class MovieController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final MovieReadService movieReadService;
    private final MovieCreateService movieCreateService;
    private final MovieUpdateService movieUpdateService;
    private final MovieDeleteService movieDeleteService;

    @GetMapping
    ResponseEntity<List<Movie>> findAll(@PageableDefault(value = Integer.MAX_VALUE)
                                                   Pageable pageable) {
        LOGGER.info("Retrieving all movies for page: {}", pageable);
        return ResponseEntity.ok(movieReadService.findMovies(pageable.getPageNumber(),
                pageable.getPageSize())
                .getContent());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Movie> findById(@PathVariable Long id) {
        LOGGER.info("Retrieving Movie by id: {}", id);
        return ResponseEntity.ok(movieReadService.findById(id));
    }

    @PostMapping
    ResponseEntity<Movie> create(@RequestBody MovieDTO dto) {
        LOGGER.info("Retrieving Movie from DTO: {}", dto);
        return ResponseEntity.ok(movieCreateService.create(dto));
    }

    @PutMapping
    void update(@RequestBody MovieDTO movieDTO, @PathVariable Long id) {
        LOGGER.info("Updating Movie of id: {} with DTO: {}", id, movieDTO);
        movieUpdateService.update(movieDTO, id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        LOGGER.info("Deleting by id Movie with id: {}", id);
        movieDeleteService.delete(id);
    }

}
