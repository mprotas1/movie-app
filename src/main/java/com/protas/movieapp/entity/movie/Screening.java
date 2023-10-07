package com.protas.movieapp.entity.movie;

import com.protas.movieapp.entity.cinema.ScreeningRoom;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "screening")
@Data
public class Screening {
    private Integer id;
    private Movie contextMovie;
    private ScreeningRoom roomDisplayedOn;

    boolean blabla() {
        return roomDisplayedOn.getSeats().stream().anyMatch(seat -> seat.getIsOccupied());
    }
}
