package com.protas.movieapp.movie;

import com.protas.movieapp.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MovieRepository extends JpaRepository<Movie, Long> {}
