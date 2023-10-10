package com.protas.movieapp.entity.cinema;

import com.protas.movieapp.entity.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScreeningRoom> screeningRooms;

    public Cinema(String name, Address address) {
        this.name = name;
        this.address = address;
        this.screeningRooms = new ArrayList<>();
    }

}
