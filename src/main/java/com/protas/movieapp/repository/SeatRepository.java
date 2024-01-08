package com.protas.movieapp.repository;

import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByScreeningRoomAndRowAndColumn(Integer id, Integer row, Integer column);
}
