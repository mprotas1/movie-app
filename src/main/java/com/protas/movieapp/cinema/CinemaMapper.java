package com.protas.movieapp.cinema;

import com.protas.movieapp.cinema.CinemaDTO;
import com.protas.movieapp.cinema.Cinema;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    Cinema fromDTO(CinemaDTO cinemaDTO);
    CinemaDTO toDTO(Cinema cinema);
}
