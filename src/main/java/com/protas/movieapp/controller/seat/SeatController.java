package com.protas.movieapp.controller.seat;

import com.protas.movieapp.dto.SeatCreationDTO;
import com.protas.movieapp.dto.SeatDTO;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.service.seat.SeatCreateService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/room/{roomId}")
@RequiredArgsConstructor
public class SeatController {
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
