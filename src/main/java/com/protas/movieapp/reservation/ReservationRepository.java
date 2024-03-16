package com.protas.movieapp.reservation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findReservationBySeatId(@Param("seat_id") Long seatId);
    Optional<List<Reservation>> findAllByCustomerId(@Param("customer_id") Long customerId, Pageable pageable);
}
