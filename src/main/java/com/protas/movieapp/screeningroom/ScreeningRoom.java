package com.protas.movieapp.screeningroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.protas.movieapp.cinema.Cinema;
import com.protas.movieapp.screening.Screening;
import com.protas.movieapp.seat.Seat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "screening_room")
@Data
@NoArgsConstructor
public class ScreeningRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "screening_screening_room",
            joinColumns = @JoinColumn(name = "screening_id"),
            inverseJoinColumns = @JoinColumn(name = "screening_room_id"))
    private List<Screening> screenings;

    public ScreeningRoom(ScreeningRoomDTO dto, Cinema cinema) {
        this.screeningRoomNumber = dto.screeningRoomNumber();
        this.roomSize = dto.size();
        this.cinema = cinema;
    }

    public void addScreening(Screening screening) {
        this.screenings.add(screening);
    }

    public void updateFromDTO(ScreeningRoomDTO dto) {
        if(dto.size() != null) this.setRoomSize(dto.size());
        if(dto.screeningRoomNumber() != null) this.setScreeningRoomNumber(dto.screeningRoomNumber());
    }
}
