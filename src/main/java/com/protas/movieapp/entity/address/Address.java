package com.protas.movieapp.entity.address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record Address(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        String city,
        String street,
        Integer number
) {}
