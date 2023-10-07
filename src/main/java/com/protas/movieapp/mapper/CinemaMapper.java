package com.protas.movieapp.mapper;

import com.protas.movieapp.dto.CinemaDTO;
import com.protas.movieapp.entity.cinema.Cinema;
import org.springframework.stereotype.Component;

@Component
public class CinemaMapper {

    public Cinema fromDTO(CinemaDTO cinemaDTO) {
        return new Cinema(cinemaDTO.name(), cinemaDTO.address());
    }

    public CinemaDTO toDTO(Cinema cinema) {
        return new CinemaDTO(cinema.getName(), cinema.getAddress());
    }

}
