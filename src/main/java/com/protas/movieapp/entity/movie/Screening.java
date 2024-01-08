package com.protas.movieapp.entity.movie;

import com.protas.movieapp.entity.cinema.ScreeningRoom;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "screening")
@Data
@NoArgsConstructor
public class Screening {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "context_movie_id")
    private Movie contextMovie;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "screening_room_id")
    private ScreeningRoom screeningRoom;

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
