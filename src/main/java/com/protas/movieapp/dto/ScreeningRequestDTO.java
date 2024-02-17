package com.protas.movieapp.dto;

import java.time.LocalDateTime;

public record ScreeningRequestDTO(Long movieId, LocalDateTime startTime) {}
