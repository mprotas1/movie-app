package com.protas.movieapp.controller;

import com.protas.movieapp.dto.SeatDTO;
import com.protas.movieapp.entity.cinema.Seat;
import com.protas.movieapp.repository.SeatRepository;
import com.protas.movieapp.testcontainers.TestContainerBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

public class SeatControllerTest extends TestContainerBase {
    @Autowired
    MockMvc mockMvc;
    @InjectMocks
    SeatRepository seatRepository;
    private SeatDTO validSeatDTO;
    private SeatDTO invalidSeatDTO;

    @BeforeEach
    void setUp() {
        validSeatDTO = new SeatDTO(11, 12);
        invalidSeatDTO = new SeatDTO(111, 111);
    }

    @Test
    public void shouldCreateSeatInScreeningRoom() {
        when(seatRepository.save(Mockito.any(Seat.class))).thenReturn(null);

    }

}
