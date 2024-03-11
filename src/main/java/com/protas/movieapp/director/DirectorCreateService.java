package com.protas.movieapp.director;

import com.protas.movieapp.director.DirectorDTO;
import com.protas.movieapp.director.Director;
import com.protas.movieapp.director.DirectorMapper;
import com.protas.movieapp.director.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorCreateService {
    private final DirectorRepository repository;
    private final DirectorMapper directorMapper;

    public DirectorDTO create(DirectorDTO dto) {
        Director saved = repository.save(directorMapper.fromDTO(dto));
        return directorMapper.toDTO(saved);
    }
}
