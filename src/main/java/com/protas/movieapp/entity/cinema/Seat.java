package com.protas.movieapp.entity.cinema;

import com.protas.movieapp.entity.reservation.Reservation;
import com.protas.movieapp.model.SeatType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer seatRowNumber;
    private Integer seatColumnNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private ScreeningRoom room;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();
}
