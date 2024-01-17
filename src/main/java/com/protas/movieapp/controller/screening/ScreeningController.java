package com.protas.movieapp.controller.screening;

import com.protas.movieapp.dto.ScreeningRequestDTO;
import com.protas.movieapp.entity.movie.Screening;
import com.protas.movieapp.service.screening.ScreeningCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/room/{roomId}/screening")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningCreateService screeningCreateService;

    @PostMapping
    public ResponseEntity<Screening> create(@RequestBody ScreeningRequestDTO screeningRequestDTO,
                                            @PathVariable Integer roomId) {
        Screening savedScreening = screeningCreateService.create(screeningRequestDTO, roomId);
        return ResponseEntity.ok(null);
    }

}
