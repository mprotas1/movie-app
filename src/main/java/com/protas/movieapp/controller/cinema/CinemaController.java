package com.protas.movieapp.controller.cinema;

import com.protas.movieapp.dto.CinemaDTO;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.service.cinema.CinemaCreateService;
import com.protas.movieapp.service.cinema.CinemaReadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final Logger logger = LoggerFactory.getLogger(CinemaController.class.getName());
    private final CinemaReadService readService;
    private final CinemaCreateService createService;

    @GetMapping
    public ResponseEntity<List<Cinema>> findAll() {
        return ResponseEntity.ok(readService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> findById(@PathVariable Long id) {
        return ResponseEntity.ok(readService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cinema> create(@RequestBody @Valid CinemaDTO cinema) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        authorities.forEach(element -> logger.info("Authority: {}", element));
        return ResponseEntity.status(HttpStatus.CREATED).body(createService.create(cinema));
    }
}
