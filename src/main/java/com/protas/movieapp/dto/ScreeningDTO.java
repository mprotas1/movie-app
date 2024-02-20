package com.protas.movieapp.dto;

import java.time.LocalDateTime;

public record ScreeningDTO(Long movieId, Long screeningRoomId, LocalDateTime startTime) {}
