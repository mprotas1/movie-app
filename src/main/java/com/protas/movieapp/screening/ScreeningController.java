package com.protas.movieapp.screening;

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
                                            @PathVariable Long roomId) {
        Screening savedScreening = screeningCreateService.create(screeningRequestDTO, roomId);
        return ResponseEntity.ok(savedScreening);
    }

}
