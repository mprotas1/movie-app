package com.protas.movieapp.director;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.protas.movieapp.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Director {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Movie> movies;
}
