package com.protas.movieapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Entity already exists")
public class EntityAlreadyExistsException extends RuntimeException {
    private Object object;

    public EntityAlreadyExistsException(Object object) {
        this.object = object;
    }

    @Override
    public String getMessage() {
        return object == null
                ? super.getMessage()
                : String.format("The object %s already exists, please provide new", object.toString());
    }

}
