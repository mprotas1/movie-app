package com.protas.movieapp.service.director;

import com.protas.movieapp.dto.DirectorDTO;
import com.protas.movieapp.entity.movie.Director;
import com.protas.movieapp.mapper.DirectorMapper;
import com.protas.movieapp.repository.DirectorRepository;
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
