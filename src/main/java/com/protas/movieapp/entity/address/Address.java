package com.protas.movieapp.entity.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String city;
        String street;
        Integer addressNumber;

        @Override
        public boolean equals(java.lang.Object o) {
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
}
