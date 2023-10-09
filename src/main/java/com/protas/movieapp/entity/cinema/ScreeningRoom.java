package com.protas.movieapp.entity.cinema;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "screening_room")
@Data
public class ScreeningRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer screeningRoomNumber;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Seat> seats;
}
