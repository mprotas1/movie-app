package com.protas.movieapp.mapper;

import com.protas.movieapp.dto.DirectorDTO;
import com.protas.movieapp.entity.movie.Director;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    Director fromDTO(DirectorDTO dto);
    DirectorDTO toDTO(Director director);
}
