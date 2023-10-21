package com.protas.movieapp.entity.cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.protas.movieapp.entity.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "cinema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public void addScreeningRoom(ScreeningRoom room) {
        if(screeningRooms == null)
            screeningRooms = new ArrayList<>();
        screeningRooms.add(room);
    }
}
