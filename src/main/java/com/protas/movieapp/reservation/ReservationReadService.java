package com.protas.movieapp.reservation;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class ReservationReadService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final ReservationRepository reservationRepository;
    private ReservationMapper mapper;

    ReservationDTO findById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .map(mapper::toDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    // TODO: implement Pageable usage
    List<ReservationDTO> findAllByCustomerId(Long customerId, Pageable pageable) {
        return reservationRepository.findAllByCustomerId(customerId, pageable).stream()
                .flatMap(optionalList -> new ArrayList<>(optionalList)
                        .stream()
                        .map(mapper::toDTO))
                .collect(Collectors.toList());
    }


    boolean reservationExistsBySeatId(Long seatId) {
        return reservationRepository.findReservationBySeatId(seatId).isPresent();
    }
}
