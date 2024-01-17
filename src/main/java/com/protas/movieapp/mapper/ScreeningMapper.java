package com.protas.movieapp.mapper;

import com.protas.movieapp.dto.ScreeningDTO;
import com.protas.movieapp.entity.movie.Screening;

public class ScreeningMapper {
    public static Screening fromDTO(ScreeningDTO dto) {
        return new Screening(dto.movie(), dto.startTime(), dto.room());
    }
}
