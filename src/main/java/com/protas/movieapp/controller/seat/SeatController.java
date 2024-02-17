package com.protas.movieapp.controller.seat;

import com.protas.movieapp.dto.SeatCreationDTO;
import com.protas.movieapp.dto.SeatDTO;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.service.seat.SeatCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room/{roomId}")
@RequiredArgsConstructor
public class SeatController {
    private final SeatCreateService seatCreateService;

    public ResponseEntity<Seat> createSeatInRoom(@RequestBody SeatCreationDTO seatCreationDTO,
                                                 @PathVariable Long roomId) {
        var seat = seatCreateService.createSeat(new SeatDTO(seatCreationDTO.row(),
                seatCreationDTO.column()),
                seatCreationDTO.seatType(),
                roomId);
        return ResponseEntity.ok(seat);
    }

}
