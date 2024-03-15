package com.protas.movieapp.reservation;

import com.protas.movieapp.seat.Seat;
import com.protas.movieapp.screening.Screening;
import com.protas.movieapp.user.User;
import com.protas.movieapp.seat.SeatReadService;
import com.protas.movieapp.screening.ScreeningReadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCreateService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final ReservationRepository reservationRepository;
    private final ScreeningReadService screeningReadService;
    private final SeatReadService seatReadService;

    @Transactional
    public ReservationDTO doReservation(ReservationDTO reservationDTO, User customer) {
        LOGGER.info("Performing reservation for user: {} for Screening: {}", customer.getId(), reservationDTO.screeningId());
        Screening screening = screeningReadService.findById(reservationDTO.screeningId());
        Seat reservationSeat = seatReadService.getSeatFromScreeningRoomAndSeatDTO(screening.getScreeningRoom(), reservationDTO.seatDTO());
        if(reservationExistsById(reservationSeat.getId())) {
            throw new RuntimeException("The reservation for this Seat already exists");
        }
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