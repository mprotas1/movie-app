package com.protas.movieapp.reservation;

import com.protas.movieapp.seat.Seat;
import com.protas.movieapp.screening.Screening;
import com.protas.movieapp.user.User;
import com.protas.movieapp.utils.price.PriceCalculator;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "screening_id", referencedColumnName = "id", nullable = false)
    private Screening movieScreening;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    private BigDecimal price;

    public void setPrice() {
        this.price = PriceCalculator.calculatePrice(this);
    }

    public static ReservationBuilder builder() {
        return new ReservationBuilder();
    }
}
