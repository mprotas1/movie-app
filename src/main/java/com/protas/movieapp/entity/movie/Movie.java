package com.protas.movieapp.entity.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity @Data
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    private Director director;

    private LocalDate dateOfPremiere;

    @Min(value = 0)
    private Integer durationInMinutes;

    @OneToMany
    private List<Screening> screeningOccurences;
}
