package com.protas.movieapp.entity.movie;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "movie")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    private Director director;
    private LocalDate dateOfPremiere;
    private Integer durationInMinutes;
}
