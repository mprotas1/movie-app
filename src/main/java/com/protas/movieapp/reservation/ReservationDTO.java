package com.protas.movieapp.reservation;

import com.protas.movieapp.seat.SeatDTO;

public record ReservationDTO(Long screeningId, SeatDTO seatDTO) {}
