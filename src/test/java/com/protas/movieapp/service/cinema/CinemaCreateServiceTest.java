package com.protas.movieapp.service.cinema;

import com.protas.movieapp.dto.CinemaDTO;
import com.protas.movieapp.entity.address.Address;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.mapper.CinemaMapper;
import com.protas.movieapp.repository.CinemaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(value = "/application-test.properties")
public class CinemaCreateServiceTest {
    @MockBean
    private CinemaRepository cinemaRepository;
    @Autowired
    private CinemaMapper mapper;
    @Autowired
    private CinemaCreateService cinemaService;
    private CinemaDTO exampleCinema;
    private Address exampleAddress;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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

    @Test
    @DisplayName(value = "Should not create Cinema due to already existing Address")
    void shouldNotCreateCinemaDueToExistingAddress() {

    }
}
