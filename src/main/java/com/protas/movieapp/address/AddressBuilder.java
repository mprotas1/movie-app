package com.protas.movieapp.address;

public class AddressBuilder {
    private final Address result;

    public AddressBuilder() {
        result = new Address();
    }

    public AddressBuilder withCity(String city) {
        result.setCity(city);
        return this;
    }

    public AddressBuilder withStreet(String street) {
        result.setStreet(street);
        return this;
    }

    public AddressBuilder withAddressNumber(Integer addressNumber) {
        result.setAddressNumber(addressNumber);
        return this;
    }

    public Address build() {
        return result;
    }
}
