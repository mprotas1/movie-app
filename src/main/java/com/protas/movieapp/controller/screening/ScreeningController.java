package com.protas.movieapp.controller.screening;

import com.protas.movieapp.dto.ScreeningRequestDTO;
import com.protas.movieapp.entity.movie.Screening;
import com.protas.movieapp.service.screening.ScreeningCreateService;
import com.protas.movieapp.service.screening.ScreeningReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/room/{roomId}/screening")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningCreateService screeningCreateService;
    private final ScreeningReadService screeningReadService;

    @GetMapping
    public ResponseEntity<List<Screening>> findAllByScreeningRoom(@PathVariable Integer roomId) {
        return ResponseEntity.ok(screeningReadService.findAllByScreeningRoom(roomId));
    }

    @PostMapping
    public ResponseEntity<Screening> create(@RequestBody ScreeningRequestDTO screeningRequestDTO,
                                            @PathVariable Integer roomId) {
        Screening savedScreening = screeningCreateService.create(screeningRequestDTO, roomId);
        return ResponseEntity.ok(savedScreening);
    }

}
