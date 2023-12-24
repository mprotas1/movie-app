package com.protas.movieapp.service.cinema;

import com.protas.movieapp.dto.CinemaDTO;
import com.protas.movieapp.entity.address.Address;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.exception.EntityAlreadyExistsException;
import com.protas.movieapp.mapper.CinemaMapper;
import com.protas.movieapp.repository.CinemaRepository;
import com.protas.movieapp.service.address.AddressReadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaCreateService {
    private final CinemaMapper mapper;
    private final AddressReadService addressService;
    private final CinemaRepository cinemaRepository;
    private final Logger logger = LoggerFactory.getLogger(CinemaCreateService.class.getName());

    @Transactional
    public Cinema create(CinemaDTO cinema) {
        throwExceptionIfAddressExists(cinema);
        Cinema cinemaEntity = mapper.fromDTO(cinema);
        logger.info("Creating the Cinema: {}...", cinemaEntity.getName());
        return cinemaRepository.save(mapper.fromDTO(cinema));
    }

    private void throwExceptionIfAddressExists(CinemaDTO cinema) throws EntityAlreadyExistsException {
        Address address = cinema.address();
        if(addressService.addressAlreadyExists(address)) {
            logger.error("The Cinema cannot have duplicate address: {}", cinema.address());
            throw new EntityAlreadyExistsException("Cinema's address already exists!");
        }
    }

}
