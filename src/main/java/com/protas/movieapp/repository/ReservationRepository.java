package com.protas.movieapp.repository;

import com.protas.movieapp.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findReservationBySeatId(@Param("seat_id") Long seatId);
}
