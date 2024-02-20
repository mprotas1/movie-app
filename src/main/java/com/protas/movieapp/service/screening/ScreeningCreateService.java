package com.protas.movieapp.service.screening;

import com.protas.movieapp.dto.MovieDTO;
import com.protas.movieapp.dto.ScreeningDTO;
import com.protas.movieapp.dto.ScreeningRequestDTO;
import com.protas.movieapp.entity.movie.Screening;
import com.protas.movieapp.mapper.ScreeningMapper;
import com.protas.movieapp.repository.ScreeningRepository;
import com.protas.movieapp.service.movie.MovieReadService;
import com.protas.movieapp.service.room.ScreeningRoomReadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ScreeningCreateService {
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomReadService screeningRoomReadService;
    private final MovieReadService movieReadService;
    private final ScreeningMapper screeningMapper = Mappers.getMapper(ScreeningMapper.class);

    public Screening create(ScreeningRequestDTO screeningRequestDTO, Integer roomId) {
        return screeningRepository.save(getScreeningFromScreeningRequestDTO(screeningRequestDTO, roomId));
    }

    private Screening getScreeningFromScreeningRequestDTO(ScreeningRequestDTO screeningRequestDTO,
                                                      Integer roomId) {
        var room = screeningRoomReadService.findById(roomId);
        var movie = movieReadService.findById(screeningRequestDTO.movieId());
        return new Screening(movie, screeningRequestDTO.startTime(), room);
    }
}
