package com.protas.movieapp.entity.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
