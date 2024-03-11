package com.protas.movieapp.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity @Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String city;
    @NotBlank
    String street;
    @Positive
    Integer addressNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(addressNumber, address.addressNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, addressNumber);
    }


    @JsonIgnore
    public boolean isValid() {
        return StringUtils.isNotBlank(city) && StringUtils.isNotBlank(street) && addressNumber >= 1;
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
    }
}
