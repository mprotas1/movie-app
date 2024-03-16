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

import java.time.DateTimeException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final ReservationRepository reservationRepository;
    private final ReservationReadService reservationReadService;
    private final ScreeningReadService screeningReadService;
    private final SeatReadService seatReadService;
    private final ReservationMapper mapper;

    @Transactional
    public ReservationDTO doReservation(ReservationDTO reservationDTO, User customer) {
        LOGGER.info("Performing reservation for user: {} for Screening: {}", customer.getId(), reservationDTO.screeningId());
        Screening screening = screeningReadService.findById(reservationDTO.screeningId());
        Seat reservationSeat = seatReadService.getSeatFromScreeningRoomAndSeatDTO(screening.getScreeningRoom(), reservationDTO.seatDTO());

        Reservation reservation = Reservation.builder()
                .withCustomer(customer)
                .withScreening(screening)
                .withSeat(reservationSeat)
                .build();

        validateReservation(reservation);

        return mapper.toDTO(reservationRepository.save(reservation));
    }

    private void validateReservation(Reservation reservation) {
        validateSeat(reservation.getSeat());
        validateReservationTime(reservation.getMovieScreening());
    }

    private void validateSeat(Seat reservationSeat) {
        if(reservationReadService.reservationExistsBySeatId(reservationSeat.getId())) {
            LOGGER.info("Could not process the Reservation - the Seat [{} {}] is already taken",
                    reservationSeat.getSeatRowNumber(),
                    reservationSeat.getSeatColumnNumber());
            throw new SeatIsAlreadyTakenException("The reservation for this Seat already exists");
        }
    }

    private void validateReservationTime(Screening screening) {
        if(LocalDateTime.now().isAfter(screening.getStartTime()))
            throw new DateTimeException("The requested Screening for Reservation is not available");
    }
}