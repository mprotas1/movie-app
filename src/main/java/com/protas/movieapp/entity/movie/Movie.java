package com.protas.movieapp.entity.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
    @PastOrPresent(message = "The date of movie's premiere should be at least present or future")
    private LocalDate dateOfPremiere;
    @Min(value = 0)
    private Integer durationInMinutes;
}
