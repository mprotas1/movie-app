package com.protas.movieapp.service.cinema;

import com.protas.movieapp.repository.CinemaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaDeleteService {
    private final Logger logger = LoggerFactory.getLogger(CinemaDeleteService.class.getName());
    private final CinemaRepository repository;

    @Transactional
    public void deleteById(Long id) {
        if(repository.findById(id).isEmpty()) {
            logger.error("The Cinema marked for deletion with {} id could not be found", id);
            throw new EntityNotFoundException("Could not find the entity with " + id + " id");
        }
        repository.deleteById(id);
    }

}
