package com.protas.movieapp.entity.cinema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Entity(name = "screening_room")
@Data
public class ScreeningRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer screeningRoomNumber;
    private Set<Seat> seats;
    private Cinema cinema;
}
