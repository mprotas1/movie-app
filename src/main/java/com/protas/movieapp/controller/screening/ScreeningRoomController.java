package com.protas.movieapp.controller.screening;

import com.protas.movieapp.dto.ScreeningRoomDTO;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.entity.cinema.ScreeningRoom;
import com.protas.movieapp.service.room.ScreeningRoomCreateService;
import com.protas.movieapp.service.room.ScreeningRoomDeleteService;
import com.protas.movieapp.service.room.ScreeningRoomReadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ScreeningRoom>> findAll(@PathVariable Long cinemaId) {
        return ResponseEntity.ok(readService.findAllByCinemaId(cinemaId));
    }

    @PostMapping
    public ResponseEntity<ScreeningRoom> create(@RequestBody @Valid ScreeningRoomDTO roomDTO,
                                         @PathVariable Long cinemaId) {
        return ResponseEntity.ok(createService.createRoomInCinema(roomDTO, cinemaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable Long cinemaId) {
        deleteService.deleteRoom(id, cinemaId);
        return ResponseEntity.noContent().build();
    }
}
