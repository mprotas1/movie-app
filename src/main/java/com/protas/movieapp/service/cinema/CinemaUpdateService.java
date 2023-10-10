package com.protas.movieapp.service.cinema;

import com.protas.movieapp.dto.CinemaDTO;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaUpdateService {
    private final CinemaRepository repository;
    private final CinemaReadService readService;

    public Cinema update(Long id, CinemaDTO cinemaDTO) {
        Cinema cinemaToUpdate = readService.findById(id);
        return repository.save(updateCinemaValues(cinemaToUpdate, cinemaDTO));
    }

    private Cinema updateCinemaValues(Cinema cinema, CinemaDTO dto) {
        cinema.setName(dto.name());

        if(dto.address() != null && cinemaAddressChanged(cinema, dto)) {
            cinema.setAddress(dto.address());
        }

        return cinema;
    }

    private boolean cinemaAddressChanged(Cinema cinema, CinemaDTO dto) {
        return !cinema.getAddress().equals(dto.address());
    }
}
