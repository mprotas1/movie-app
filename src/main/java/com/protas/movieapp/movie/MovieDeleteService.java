package com.protas.movieapp.movie;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class MovieDeleteService {
    private final MovieRepository repository;
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
