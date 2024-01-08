package com.protas.movieapp.mapper;

import com.protas.movieapp.dto.ReservationDTO;
import com.protas.movieapp.dto.SeatDTO;
import com.protas.movieapp.entity.reservation.Reservation;
import com.protas.movieapp.entity.user.User;

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
