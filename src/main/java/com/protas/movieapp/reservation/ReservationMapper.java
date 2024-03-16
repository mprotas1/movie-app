package com.protas.movieapp.reservation;

import com.protas.movieapp.seat.SeatDTO;
import com.protas.movieapp.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ReservationMapper {

    Reservation reservationFromReservationDTOAndCustomer(ReservationDTO reservationDTO, User customer);

    /*{
        if(reservationDTO == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        return reservation;
    }
    */
   ReservationDTO toDTO(Reservation reservation);
}
