package com.protas.movieapp.repository;

import com.protas.movieapp.entity.cinema.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByRoomIdAndSeatRowNumberAndSeatColumnNumber(@Param(value = "room_id") Integer roomId,
                                               @Param(value = "seat_row") Integer row,
                                               @Param(value = "seat_column") Integer column);

    List<Seat> findAllByRoomId(Long roomId);
}
