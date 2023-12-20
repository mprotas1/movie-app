package com.protas.movieapp.service.cinema;

import com.protas.movieapp.dto.CinemaDTO;
import com.protas.movieapp.entity.address.Address;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.exception.EntityAlreadyExistsException;
import com.protas.movieapp.mapper.CinemaMapper;
import com.protas.movieapp.repository.CinemaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(value = "/application-test.properties")
public class CinemaCreateServiceTest {
    @Autowired
    private CinemaMapper mapper;
    @Autowired
    private CinemaCreateService cinemaService;
    @MockBean
    private CinemaRepository cinemaRepository;
    private CinemaDTO exampleCinema;
    private Address exampleAddress;

    @BeforeEach
    void setUp() {
        exampleAddress = new Address();
        exampleAddress.setCity("Szczecin");
        exampleAddress.setStreet("Aleja Wyzwolenia");
        exampleAddress.setAddressNumber(25);
        exampleCinema = new CinemaDTO("Multikino Szczecin", exampleAddress);
    }

    @Test
    @DisplayName(value = "Should create Cinema with all data correct")
    void shouldCreateCinemaWithDataCorrect() {
        Cinema cinema = mapper.fromDTO(exampleCinema);
        when(cinemaRepository.save(cinema))
                .thenReturn(cinema);
        Cinema createdCinema = cinemaService.create(exampleCinema);
        assertNotNull(createdCinema);
        assertSame(exampleAddress, createdCinema.getAddress());
        verify(cinemaRepository, times(1)).save(cinema);
    }

    @Test()
    @DisplayName(value = "Should not create Cinema due to already existing Address")
    void shouldNotCreateCinemaDueToExistingAddress() {
        Cinema cinema = mapper.fromDTO(exampleCinema);
        when(cinemaRepository.save(cinema)).thenReturn(cinema);
        Cinema createdCinema = cinemaService.create(exampleCinema);

        when(cinemaRepository.save(cinema)).thenThrow(EntityAlreadyExistsException.class);
        assertThrows(EntityAlreadyExistsException.class, () -> cinemaService.create(exampleCinema));

        verify(cinemaRepository, times(2)).save(cinema);
    }
}