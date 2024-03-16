package com.protas.movieapp.reservation;

import com.protas.movieapp.exception.EntityAlreadyExistsException;

public class SeatIsAlreadyTakenException extends EntityAlreadyExistsException {
    public SeatIsAlreadyTakenException(Object object) {
        super(object);
    }


}
