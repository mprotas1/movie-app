package com.protas.movieapp.screening;

import com.protas.movieapp.movie.MovieReadService;
import com.protas.movieapp.screeningroom.ScreeningRoomReadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ScreeningCreateService {
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomReadService screeningRoomReadService;
    private final MovieReadService movieReadService;

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
