package com.protas.movieapp.service.director;

import com.protas.movieapp.dto.DirectorDTO;
import com.protas.movieapp.entity.movie.Director;
import com.protas.movieapp.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorUpdateService {
    private final DirectorReadService directorReadService;
    private final DirectorRepository repository;

    public Director update(Long id, DirectorDTO dto) {
        return repository.save(setUpDirector(id, dto));
    }

    private Director setUpDirector(Long id, DirectorDTO dto) {
        Director directorToBeUpdated = directorReadService.findDirectorById(id);
        directorToBeUpdated.setFirstName(dto.firstName());
        directorToBeUpdated.setLastName(dto.lastName());
        return directorToBeUpdated;
    }
}
