package com.protas.movieapp.mapper;

import com.protas.movieapp.dto.ScreeningDTO;
import com.protas.movieapp.entity.movie.Screening;
import org.mapstruct.Mapper;

@Mapper
public interface ScreeningMapper {
    Screening fromDTO(ScreeningDTO dto);
}
