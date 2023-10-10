package com.protas.movieapp.dto;

import com.protas.movieapp.entity.address.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CinemaDTO(
        @NotBlank @Size(min = 5)
        String name,
        Address address
) {}
