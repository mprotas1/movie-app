package com.protas.movieapp.dto;

import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.entity.movie.Screening;

public record ReservationDTO(Long screeningId, SeatDTO seatDTO) {
}
