package com.protas.movieapp.entity.movie;

import com.protas.movieapp.entity.cinema.ScreeningRoom;
import com.protas.movieapp.entity.reservation.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "context_movie_id")
    private Movie contextMovie;

    @NotNull
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "screening_room_id")
    private ScreeningRoom screeningRoom;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservations;

    public Screening(Movie movie, LocalDateTime startTime, ScreeningRoom room) {
        this.contextMovie = movie;
        this.startTime = startTime;
        this.setScreeningRoom(room);
        setEndTimeByMovieDuration();
    }

    private void setEndTimeByMovieDuration() {
        Integer duration = contextMovie.getDurationInMinutes();
        var resultTime = startTime.plusMinutes((long) duration);
        setEndTime(resultTime);
    }

}
