package com.protas.movieapp.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RestExceptionMessage {
    private String path;
    private String message;
    private LocalDateTime dateTime;
}
