package com.protas.movieapp.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { EntityAlreadyExistsException.class, EntityNotFoundException.class })
    protected ResponseEntity<RestExceptionMessage> handleAddressAlreadyExists(RuntimeException ex,
                                                                              WebRequest request) {
        RestExceptionMessage response = RestExceptionMessage.builder()
                .path(request.getContextPath())
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
