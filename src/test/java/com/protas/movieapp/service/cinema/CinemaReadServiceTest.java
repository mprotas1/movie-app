package com.protas.movieapp.service.cinema;

import com.protas.movieapp.repository.CinemaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CinemaReadServiceTest {

    @Autowired
    CinemaReadService cinemaReadService;

    @MockBean
    CinemaRepository cinemaRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void shouldGetAllCinemas() {

    }
}
