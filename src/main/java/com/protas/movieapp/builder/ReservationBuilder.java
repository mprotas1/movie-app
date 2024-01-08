package com.protas.movieapp.builder;

import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.entity.movie.Screening;
import com.protas.movieapp.entity.reservation.Reservation;
import com.protas.movieapp.entity.user.User;

public class ReservationBuilder {

    private Reservation reservation = new Reservation();

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
