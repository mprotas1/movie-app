package com.protas.movieapp.entity.movie;

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
    private List<Movie> movies;
}
