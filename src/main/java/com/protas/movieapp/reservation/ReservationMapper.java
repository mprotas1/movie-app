package com.protas.movieapp.reservation;

import com.protas.movieapp.seat.SeatDTO;
import com.protas.movieapp.user.User;

public class ReservationMapper {

    public static Reservation reservationFromReservationDTOAndCustomer(ReservationDTO reservationDTO,
                                                                       User customer) {
        if(reservationDTO == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        return reservation;
    }

    public static ReservationDTO toDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getMovieScreening().getId(),
                new SeatDTO(reservation.getSeat().getSeatRowNumber(),
                        reservation.getSeat().getSeatColumnNumber()));
    }

}
