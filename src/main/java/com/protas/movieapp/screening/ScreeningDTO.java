package com.protas.movieapp.screening;

import java.time.LocalDateTime;

public record ScreeningDTO(Long movieId, Long screeningRoomId, LocalDateTime startTime) {}
