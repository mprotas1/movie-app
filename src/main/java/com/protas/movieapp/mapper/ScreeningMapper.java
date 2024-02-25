package com.protas.movieapp.mapper;

import com.protas.movieapp.dto.ScreeningDTO;
import com.protas.movieapp.entity.movie.Screening;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScreeningMapper {
    ScreeningMapper INSTANCE = Mappers.getMapper(ScreeningMapper.class);
    Screening fromDTO(ScreeningDTO dto);
}
