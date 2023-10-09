package com.protas.movieapp.service.cinema;

import com.protas.movieapp.dto.CinemaDTO;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.exception.AddressAlreadyExistsException;
import com.protas.movieapp.mapper.CinemaMapper;
import com.protas.movieapp.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaCreateService {
    private final CinemaMapper mapper;
    private final CinemaReadService readService;
    private final CinemaRepository cinemaRepository;
    private final Logger logger = LoggerFactory.getLogger(CinemaCreateService.class.getName());

    public Cinema create(CinemaDTO cinema) {
        if(addressAlreadyExists(cinema)) {
            logger.error("The Cinema cannot have duplicate address: {}", cinema.address());
            throw new AddressAlreadyExistsException("Cinema's address already exists!");
        }
        Cinema cinemaEntity = mapper.fromDTO(cinema);
        logger.info("Creating the Cinema with id: {} : {}...", cinemaEntity.getId(), cinemaEntity.getName());
        return cinemaRepository.save(mapper.fromDTO(cinema));
    }

    private boolean addressAlreadyExists(CinemaDTO cinema) {
        return readService.findAll().stream()
                .anyMatch(c -> c.getAddress()
                        .equals(cinema.address())
                );
    }
}
