package com.protas.movieapp.seat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/room/{roomId}")
@RequiredArgsConstructor
class SeatController {
    private final SeatCreateService seatCreateService;

    @PostMapping
    public ResponseEntity<Seat> createSeatInRoom(@RequestBody SeatCreationDTO seatCreationDTO,
                                                 @PathVariable Long roomId) {
        var seat = seatCreateService.createSeat(new SeatDTO(seatCreationDTO.row(),
                seatCreationDTO.column()),
                seatCreationDTO.seatType(),
                roomId);
        return ResponseEntity.ok(seat);
    }
}
