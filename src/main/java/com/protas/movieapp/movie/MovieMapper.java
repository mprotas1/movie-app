package com.protas.movieapp.movie;

import com.protas.movieapp.director.DirectorReadService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    @Autowired
    private DirectorReadService directorReadService;

    public abstract Movie fromDTO(MovieDTO dto);
    public abstract MovieDTO toDTO(Movie movie);
}
