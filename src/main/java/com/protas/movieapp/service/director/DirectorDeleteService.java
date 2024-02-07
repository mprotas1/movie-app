package com.protas.movieapp.service.director;

import com.protas.movieapp.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorDeleteService {
    private final DirectorRepository repository;

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
