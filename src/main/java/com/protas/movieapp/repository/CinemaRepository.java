package com.protas.movieapp.repository;

import com.protas.movieapp.entity.cinema.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
}
