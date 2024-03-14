package com.protas.movieapp.screeningroom;

import com.protas.movieapp.screeningroom.ScreeningRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreeningRoomRepository extends JpaRepository<ScreeningRoom, Long> {
    Optional<List<ScreeningRoom>> findByCinemaId(Long cinemaId);
    Optional<ScreeningRoom> findByScreeningRoomNumberAndCinemaId(Integer screeningRoomNumber, Long cinemaId);
    void deleteByIdAndCinemaId(Long id, Long cinemaId);
    Optional<ScreeningRoom> findByIdAndCinemaId(Long roomId, Long cinemaId);
}
