package com.protas.movieapp.service.cinema;

import com.protas.movieapp.address.Address;
import com.protas.movieapp.cinema.Cinema;
import com.protas.movieapp.cinema.CinemaMapper;
import com.protas.movieapp.cinema.CinemaReadService;
import com.protas.movieapp.config.CinemaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class CinemaReadServiceTest {

    @Autowired
    CinemaReadService cinemaReadService;

    @Autowired
    CinemaMapper mapper;

    @MockBean
    CinemaRepository cinemaRepository;

    Cinema defaultTestCinema;

    @BeforeEach
    void setUp() {
        defaultTestCinema = Cinema.builder()
                .withName("Default name")
                .withAddress(new Address(1L, "Default", "Default", 12))
                .buildValidCinema();
    }

    @Test
    void shouldGetAllCinemas() {
        List<Cinema> cinemas = List.of(defaultTestCinema, defaultTestCinema);
        Page<Cinema> cinemasPage = new PageImpl<>(cinemas);

        when(cinemaRepository.findAll(Pageable.ofSize(100))).thenReturn(cinemasPage);

        List<Cinema> returnedCinemas = cinemaReadService.findAll(Pageable.ofSize(100));
        assertNotNull(returnedCinemas);
        assertIterableEquals(cinemas, returnedCinemas);
        assertEquals(cinemas.size(), returnedCinemas.size());
    }

    // TODO: return empty list of cinemas

    @Test
    void shouldGetCinemaById() {
        final long id = 1;
        when(cinemaRepository.findById(id)).thenReturn(Optional.ofNullable(defaultTestCinema));

        Cinema result = cinemaReadService.findById(id);

        assertNotNull(result);
    }

    @Test
    void shouldNotGetCinemaByIdAndThrowException() {
        final long id = 999;
        when(cinemaRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(
                        Mockito.nullable(Cinema.class)
                ));

        assertThrows(EntityNotFoundException.class, () -> cinemaReadService.findById(id));
    }
}
