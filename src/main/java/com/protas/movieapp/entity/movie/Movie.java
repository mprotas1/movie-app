package com.protas.movieapp.entity.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity(name = "movie")
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Director director;
    private LocalDate dateOfPremiere;
    private Integer durationInMinutes;
}
