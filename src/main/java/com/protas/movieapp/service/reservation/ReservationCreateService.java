package com.protas.movieapp.service.reservation;

import com.protas.movieapp.dto.ReservationDTO;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.entity.movie.Screening;
import com.protas.movieapp.entity.reservation.Reservation;
import com.protas.movieapp.entity.user.User;
import com.protas.movieapp.mapper.ReservationMapper;
import com.protas.movieapp.repository.ReservationRepository;
import com.protas.movieapp.service.seat.SeatReadService;
import com.protas.movieapp.service.screening.ScreeningReadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCreateService {

    private final ReservationRepository reservationRepository;
    private final ScreeningReadService screeningReadService;
    private final SeatReadService seatReadService;

    @Transactional
    public ReservationDTO doReservation(ReservationDTO reservationDTO, User customer) {
        Screening screening = screeningReadService.findById(reservationDTO.screeningId());
        Seat reservationSeat = seatReadService.getSeatFromScreeningRoomAndSeatDTO(screening.getScreeningRoom(), reservationDTO.seatDTO());
        if(reservationExistsById(reservationSeat.getId())) throw new RuntimeException("The reservation for this Seat already exists");
        Reservation reservation = Reservation.builder()
                .withCustomer(customer)
                .withScreening(screening)
                .withSeat(reservationSeat)
                .build();
        return ReservationMapper.toDTO(reservationRepository.save(reservation));
    }

    private boolean reservationExistsById(Long seatId) {
        return reservationRepository.findReservationBySeatId(seatId).isPresent();
    }

}