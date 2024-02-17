package com.protas.movieapp.dto;

import com.protas.movieapp.entity.cinema.ScreeningRoom;
import com.protas.movieapp.entity.movie.Movie;

import java.time.LocalDateTime;

public record ScreeningDTO(Movie movie, ScreeningRoom room, LocalDateTime startTime) {}
