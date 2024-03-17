package com.protas.movieapp.screening;

import com.protas.movieapp.movie.MovieReadService;
import com.protas.movieapp.screeningroom.ScreeningRoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ScreeningCreateService {
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final MovieReadService movieReadService;

    public Screening create(ScreeningRequestDTO screeningRequestDTO, Long roomId) {
        var screening = getScreeningFromScreeningRequestDTO(screeningRequestDTO, roomId);
        return screeningRepository.save(screening);
    }

    private Screening getScreeningFromScreeningRequestDTO(ScreeningRequestDTO screeningRequestDTO,
                                                      Long roomId) {
        var room = screeningRoomRepository.findById(roomId).orElseThrow(EntityNotFoundException::new);
        var movie = movieReadService.findById(screeningRequestDTO.movieId());
        return new Screening(movie, screeningRequestDTO.startTime(), room);
    }
}
