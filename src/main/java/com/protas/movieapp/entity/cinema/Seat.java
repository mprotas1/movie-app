package com.protas.movieapp.entity.cinema;

import com.protas.movieapp.builder.SeatBuilder;
import com.protas.movieapp.dto.SeatDTO;
import com.protas.movieapp.entity.reservation.Reservation;
import com.protas.movieapp.model.SeatType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_row")
    private Integer seatRowNumber;

    @Column(name = "seat_column")
    private Integer seatColumnNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private ScreeningRoom room;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    public boolean equalsDTO(SeatDTO seatDTO) {
        return Objects.equals(seatDTO.row(), this.getSeatRowNumber()) && Objects.equals(seatDTO.column(), this.getSeatColumnNumber());
    }

    public static SeatBuilder builder() {
        return new SeatBuilder();
    }
}
