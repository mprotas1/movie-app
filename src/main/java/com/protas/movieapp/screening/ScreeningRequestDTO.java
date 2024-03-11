package com.protas.movieapp.screening;

import java.time.LocalDateTime;

public record ScreeningRequestDTO(Long movieId, LocalDateTime startTime) {}
