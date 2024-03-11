package com.protas.movieapp.director;

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
