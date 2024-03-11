package com.protas.movieapp.cinema;

import com.protas.movieapp.address.Address;
import io.micrometer.common.util.StringUtils;

public class CinemaBuilder {
    private String name;
    private Address address;

    public CinemaBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CinemaBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public Cinema buildValidCinema() {
        if(fieldsAreValid()) return new Cinema(name, address);

        // TODO: implement custom Exception to delegate on invalid input to builder
        throw new RuntimeException();
    }

    private boolean fieldsAreValid() {
        return !StringUtils.isBlank(name) && address.isValid();
    }

}
