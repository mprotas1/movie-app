package com.protas.movieapp.dto;

import com.protas.movieapp.model.SeatType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record SeatCreationDTO(@Size(max = 100) Integer row,
                              @Size(max = 100) Integer column,
                              @Enumerated(EnumType.STRING)
                              SeatType seatType
) {}
