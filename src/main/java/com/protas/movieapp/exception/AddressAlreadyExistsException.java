package com.protas.movieapp.exception;

import com.protas.movieapp.entity.address.Address;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Address already exists")
public class AddressAlreadyExistsException extends RuntimeException {

    private Address address;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public AddressAlreadyExistsException() {
        super();
    }

    public AddressAlreadyExistsException(String message) {
        super(message);
    }

    public AddressAlreadyExistsException(Address address) {
        this.address = address;
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     *
     *  --- CUSTOMIZED with ADDRESS parameter ---
     *
     */
    @Override
    public String getMessage() {
        return address == null
                ? super.getMessage()
                : String.format("The address %s already exists, please provide new", address);
    }

}
