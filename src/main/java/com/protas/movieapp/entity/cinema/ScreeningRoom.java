package com.protas.movieapp.entity.cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "screening_room")
@Data
@NoArgsConstructor
public class ScreeningRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer screeningRoomNumber;

    @ManyToOne(optional = true)
    @JoinColumn(name = "cinema_id", nullable = false)
    @JsonIgnore
    private Cinema cinema;

    public ScreeningRoom(Integer screeningRoomNumber) {
        this.screeningRoomNumber = screeningRoomNumber;
    }

    public ScreeningRoom(Integer screeningRoomNumber, Cinema cinema) {
        this.screeningRoomNumber = screeningRoomNumber;
        this.cinema = cinema;
    }
}
