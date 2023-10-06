package com.protas.movieapp.entity;

import jakarta.persistence.Entity;

import java.util.Set;

@Entity(name = "screening_room")
public class ScreeningRoom {
    private Integer id;
    private Integer screeningRoomNumber;
    private Set<Seat> seats;
    private Cinema cinema;
}
