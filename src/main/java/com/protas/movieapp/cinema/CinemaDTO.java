package com.protas.movieapp.cinema;

import com.protas.movieapp.address.Address;

public record CinemaDTO(
        String name,
        Address address
) {}
