package com.protas.movieapp.dto;

import com.protas.movieapp.entity.movie.Director;

public record MovieDTO(String title, String description, Director director) {}
