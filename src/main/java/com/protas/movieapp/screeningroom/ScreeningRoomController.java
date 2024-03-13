package com.protas.movieapp.screeningroom;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinema/{cinemaId}/room")
@RequiredArgsConstructor
public class ScreeningRoomController {
    private final ScreeningRoomReadService readService;
    private final ScreeningRoomCreateService createService;
    private final ScreeningRoomDeleteService deleteService;

    @GetMapping
    public ResponseEntity<List<ScreeningRoomDTO>> findAll(@PathVariable Long cinemaId) {
        return ResponseEntity.ok(readService.findAllByCinemaId(cinemaId));
    }

    @PostMapping
    public ResponseEntity<ScreeningRoomDTO> create(@RequestBody @Valid ScreeningRoomDTO roomDTO,
                                         @PathVariable Long cinemaId) {
        return ResponseEntity.ok(createService.createRoomInCinema(roomDTO, cinemaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable Long cinemaId) {
        deleteService.deleteRoom(id, cinemaId);
        return ResponseEntity.noContent().build();
    }
}
