package com.protas.movieapp.service.director;

import com.protas.movieapp.dto.DirectorDTO;
import com.protas.movieapp.entity.movie.Director;
import com.protas.movieapp.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorCreateService {
    private final DirectorRepository repository;

    public Director create(DirectorDTO dto) {
        Director director = new Director();
        director.setFirstName(dto.firstName());
        director.setLastName(dto.lastName());
        return repository.save(director);
    }
}
