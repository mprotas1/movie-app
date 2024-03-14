package com.protas.movieapp.screeningroom;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
class ScreeningRoomController {
    private final ScreeningRoomCreateService createService;
    private final ScreeningRoomReadService readService;
    private final ScreeningRoomUpdateService updateService;
    private final ScreeningRoomDeleteService deleteService;

    @GetMapping
    public ResponseEntity<List<ScreeningRoomDTO>> findAll(@RequestParam Long cinemaId) {
        return ResponseEntity.ok(readService.findAllByCinemaId(cinemaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreeningRoomDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(readService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ScreeningRoomDTO> create(@RequestBody @Valid ScreeningRoomDTO roomDTO,
                                                   @RequestParam Long cinemaId) {
        return ResponseEntity.ok(createService.createRoomInCinema(roomDTO, cinemaId));
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<ScreeningRoomDTO> update(@RequestBody @Valid ScreeningRoomDTO roomDTO,
                                                   @PathVariable Long roomId) {
        var resultDTO = updateService.update(roomDTO, roomId);
        return ResponseEntity.ok(resultDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
