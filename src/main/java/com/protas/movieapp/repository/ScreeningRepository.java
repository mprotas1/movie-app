package com.protas.movieapp.repository;

import com.protas.movieapp.entity.movie.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
