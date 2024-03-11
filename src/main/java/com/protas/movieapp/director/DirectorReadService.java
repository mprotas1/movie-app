package com.protas.movieapp.director;

import com.protas.movieapp.director.Director;
import com.protas.movieapp.director.DirectorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorReadService {
    private final DirectorRepository directorRepository;

    public Director findDirectorById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Director> findAll(Pageable pageable) {
        return directorRepository.findAll(pageable)
                .getContent();
    }
}
