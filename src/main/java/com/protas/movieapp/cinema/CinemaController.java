package com.protas.movieapp.cinema;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaReadService readService;
    private final CinemaCreateService createService;
    private final CinemaDeleteService deleteService;
    private final CinemaUpdateService updateService;

    @GetMapping
    public ResponseEntity<List<Cinema>> findAll(@PageableDefault(value = Integer.MAX_VALUE)
                                                    Pageable pageable) {
        List<Cinema> cinemas = readService.findAll(pageable);
        return ResponseEntity.ok(cinemas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> findById(@PathVariable Long id) {
        return ResponseEntity.ok(readService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cinema> create(@RequestBody @Valid CinemaDTO cinema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createService.create(cinema));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cinema> update(@PathVariable Long id,
                                         @RequestBody @Valid CinemaDTO cinema) {
        return ResponseEntity.ok().body(updateService.update(id, cinema));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        deleteService.deleteById(id);
    }
}
