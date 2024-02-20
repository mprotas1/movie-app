package com.protas.movieapp.repository;

import com.protas.movieapp.entity.movie.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    Optional<Screening> findByContextMovieId(Long movieId);
    List<Screening> findAllByScreeningRoomId(Integer screeningRoomId);
}
