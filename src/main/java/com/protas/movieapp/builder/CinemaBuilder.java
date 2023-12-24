package com.protas.movieapp.builder;

import com.protas.movieapp.entity.address.Address;
import com.protas.movieapp.entity.cinema.Cinema;
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
        if(fieldsAreValid())
            return new Cinema(name, address);

        // TODO: implement custom Exception to delegate on invalid input to builder
        throw new RuntimeException();
    }

    private boolean fieldsAreValid() {
        return !StringUtils.isBlank(name) && address.isValidAddress();
    }

}
