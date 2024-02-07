package com.protas.movieapp.mapper;

import com.protas.movieapp.dto.DirectorDTO;
import com.protas.movieapp.entity.movie.Director;

public class DirectorMapper {
    public static Director fromDTO(DirectorDTO dto) {
        Director director = new Director();
        director.setFirstName(dto.firstName());
        director.setLastName(dto.lastName());

        return director;
    }
}
