package com.protas.movieapp.entity.cinema;

import com.protas.movieapp.entity.address.Address;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "cinema")
@Data @Builder
public class Cinema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScreeningRoom> screeningRooms;

    public Cinema(String name, Address address) {
        this.name = name;
        this.address = address;
        this.screeningRooms = new ArrayList<>();
    }
}
