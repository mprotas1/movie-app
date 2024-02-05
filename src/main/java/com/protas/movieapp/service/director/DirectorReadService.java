package com.protas.movieapp.service.director;

import com.protas.movieapp.entity.movie.Director;
import com.protas.movieapp.repository.DirectorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorReadService {
    private final DirectorRepository directorRepository;

    public Director findDirectorById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

}
