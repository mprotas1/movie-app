package com.protas.movieapp.reservation;

import com.protas.movieapp.seat.SeatDTO;
import com.protas.movieapp.user.User;
import com.protas.movieapp.user.UserAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/reservation")
@RequiredArgsConstructor
class ReservationController {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class.getName());
    private final ReservationFacade reservationFacade;
    private final ReservationReadService reservationReadService;
    private final UserAuthService userService;

    @PostMapping
    ResponseEntity<ReservationDTO> doReservation(@RequestBody ReservationDTO reservationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserFromAuthentication(authentication);

        LOGGER.info("Performing reservation for user: {}", authentication.getPrincipal());

        var reservation = reservationFacade.doReservation(reservationDTO, currentUser);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    ResponseEntity<List<ReservationDTO>> findForCustomer(@PageableDefault Pageable pageable,
                                                         @RequestParam Long userId) {
        var reservations = reservationReadService.findAllByCustomerId(userId, pageable);
        LOGGER.info("Retrieved {} reservations for User with id {}", reservations, userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{reservationId}")
    ResponseEntity<ReservationDTO> findById(@PathVariable Long reservationId) {
        var reservation = reservationReadService.findById(reservationId);
        LOGGER.info("Retrieved reservation of id: {} with body:\n{}", reservationId, reservation);
        return ResponseEntity.ok(reservation);
    }
}
