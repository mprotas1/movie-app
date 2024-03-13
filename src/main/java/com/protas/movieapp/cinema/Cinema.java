package com.protas.movieapp.cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.protas.movieapp.address.Address;
import com.protas.movieapp.screeningroom.ScreeningRoom;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "cinema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 5)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonIgnore
    private List<ScreeningRoom> screeningRooms = new ArrayList<>();

    public Cinema(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public static CinemaBuilder builder() {
        return new CinemaBuilder();
    }
}
