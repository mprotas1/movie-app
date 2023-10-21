package com.protas.movieapp.entity.cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.protas.movieapp.dto.ScreeningRoomDTO;
import com.protas.movieapp.model.RoomSize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "screening_room")
@Data
@NoArgsConstructor
public class ScreeningRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer screeningRoomNumber;
    @Enumerated(EnumType.STRING)
    private RoomSize roomSize;

    @ManyToOne
    @JoinColumn(name = "cinema_id",
            nullable = false)
    @JsonIgnore
    private Cinema cinema;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Seat> seats;

    public ScreeningRoom(ScreeningRoomDTO dto, Cinema cinema) {
        this.screeningRoomNumber = dto.screeningRoomNumber();
        this.roomSize = dto.size();
        this.cinema = cinema;
    }

    @Override
    public String toString() {
        return "ScreeningRoom{" +
                "id=" + id +
                ", screeningRoomNumber=" + screeningRoomNumber +
                ", roomSize=" + roomSize.toString() +
                '}';
    }
}
