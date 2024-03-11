package com.protas.movieapp.reservation;

import com.protas.movieapp.seat.Seat;
import com.protas.movieapp.screening.Screening;
import com.protas.movieapp.user.User;

public class ReservationBuilder {
    private final Reservation reservation = new Reservation();

    public ReservationBuilder withCustomer(User customer) {
        reservation.setCustomer(customer);
        return this;
    }

    public ReservationBuilder withScreening(Screening screening) {
        reservation.setMovieScreening(screening);
        return this;
    }

    public ReservationBuilder withSeat(Seat seat) {
        reservation.setSeat(seat);
        return this;
    }

    public Reservation build() {
        if(reservation.getSeat() == null) {
            throw new NullPointerException();
        }

        reservation.setPrice();
        return reservation;
    }
}
