package com.protas.movieapp.controller.reservation;

import com.protas.movieapp.dto.ReservationDTO;
import com.protas.movieapp.entity.user.User;
import com.protas.movieapp.service.auth.UserAuthService;
import com.protas.movieapp.service.reservation.ReservationCreateService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/cinema/{cinemaId}/room/{roomId}/")
@RequiredArgsConstructor
public class ReservationController {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class.getName());
    private final ReservationCreateService reservationCreateService;
    private final UserAuthService userService;

    @PostMapping
    public ResponseEntity<ReservationDTO> doReservation(@RequestBody @NotNull ReservationDTO reservationDTO) {
        // Need a user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserFromAuthentication(authentication);
        LOGGER.info("Performing reservation for user: {}",
                authentication.getPrincipal());

        // Need to register his reservation
        ReservationDTO reservation = reservationCreateService.doReservation(reservationDTO, currentUser);
        return ResponseEntity.ok(reservation);
    }
}
