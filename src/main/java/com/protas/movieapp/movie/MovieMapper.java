package com.protas.movieapp.movie;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    public abstract Movie fromDTO(MovieDTO dto);
    public abstract MovieDTO toDTO(Movie movie);
}
