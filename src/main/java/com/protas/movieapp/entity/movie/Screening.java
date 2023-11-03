package com.protas.movieapp.entity.movie;

import com.protas.movieapp.entity.cinema.ScreeningRoom;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "screening")
@Data
public class Screening {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    private Movie contextMovie;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToMany
    @JoinTable(name = "screening_screening_room",
            joinColumns = @JoinColumn(name = "screening_id"),
            inverseJoinColumns = @JoinColumn(name = "screening_room_id"))
    private List<ScreeningRoom> screeningRooms = new ArrayList<>();

    public Screening() {

    }

    public Screening(Movie movie, LocalDateTime startTime) {
        this.contextMovie = movie;
        this.startTime = startTime;
        setEndTimeByMovieDuration();
    }

    private void setEndTimeByMovieDuration() {
        Integer duration = contextMovie.getDurationInMinutes();
        var resultTime = startTime.plusMinutes((long) duration);
        setEndTime(resultTime);
    }
}
