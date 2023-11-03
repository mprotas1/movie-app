package com.protas.movieapp.entity.ticket;


import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// TODO: implement Ticket entity to perform reservations of screenings
@Entity
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    private Seat seat;

    private boolean isReserved;
}
