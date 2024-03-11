package com.protas.movieapp.director;

import com.protas.movieapp.director.DirectorDTO;
import com.protas.movieapp.director.Director;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    Director fromDTO(DirectorDTO dto);
    DirectorDTO toDTO(Director director);
}
