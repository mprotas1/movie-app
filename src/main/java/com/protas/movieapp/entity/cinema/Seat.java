package com.protas.movieapp.entity.cinema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity(name = "seat")
@Data
public class Seat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rowNumber;
    private Integer columnNumber;
    private Boolean isOccupied;
    private ScreeningRoom screeningRoom;
}
