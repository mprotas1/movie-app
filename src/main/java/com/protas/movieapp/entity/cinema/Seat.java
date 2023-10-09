package com.protas.movieapp.entity.cinema;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "seat")
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rowNumber;
    private Integer columnNumber;
    private Boolean isOccupied;
    @ManyToOne
    private ScreeningRoom screeningRoom;
}
