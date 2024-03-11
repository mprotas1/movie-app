package com.protas.movieapp.screening;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScreeningMapper {
    ScreeningMapper INSTANCE = Mappers.getMapper(ScreeningMapper.class);
    Screening fromDTO(ScreeningDTO dto);
}
