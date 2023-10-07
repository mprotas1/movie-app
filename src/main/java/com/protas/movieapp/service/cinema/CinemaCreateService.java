package com.protas.movieapp.service.cinema;

import com.protas.movieapp.dto.CinemaDTO;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.mapper.CinemaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaCreateService {
    private final CinemaMapper mapper;
    public Cinema create(CinemaDTO cinema) {
        return mapper.fromDTO(cinema);
    }
}
