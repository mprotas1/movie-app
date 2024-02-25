package com.protas.movieapp.controller;

import com.protas.movieapp.entity.address.Address;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.repository.CinemaRepository;
import com.protas.movieapp.testcontainers.TestContainerBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CinemaControllerTest extends TestContainerBase {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CinemaRepository repository;
    private Cinema templateCinema;

    @BeforeEach
    public void setUp() {
        templateCinema = Cinema.builder()
                .withName("Multikino")
                .withAddress(Address.builder()
                        .withCity("Szczecin")
                        .withStreet("Aleja Zwycięstwa")
                        .withAddressNumber(1)
                        .build())
                .buildValidCinema();
        templateCinema.setId(1L);
    }

    @Test
    void shouldGetSingleCinema() throws Exception {
        final long cinemaId = 1L;
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(templateCinema));

        mockMvc.perform(get("/api/cinema/{id}", cinemaId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Math.toIntExact(cinemaId))));
    }

    @Test
    void shouldGetAllCinemas() throws Exception {
        Page<Cinema> cinemas = Mockito.mock(Page.class);
        when(repository.findAll(Mockito.mock(Pageable.class))).thenReturn(cinemas);

        mockMvc.perform(get("/api/cinema"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
