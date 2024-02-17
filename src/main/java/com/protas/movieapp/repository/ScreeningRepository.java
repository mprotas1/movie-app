package com.protas.movieapp.repository;

import com.protas.movieapp.entity.movie.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    Optional<Screening> findByContextMovieId(Long movieId);
    List<Screening> findAllByScreeningRoomId(Integer screeningRoomId);
}
