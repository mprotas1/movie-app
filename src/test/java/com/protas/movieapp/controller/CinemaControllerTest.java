package com.protas.movieapp.controller;

import com.protas.movieapp.builder.CinemaBuilder;
import com.protas.movieapp.entity.address.Address;
import com.protas.movieapp.entity.cinema.Cinema;
import com.protas.movieapp.repository.CinemaRepository;
import com.protas.movieapp.testcontainers.TestContainerBase;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WithMockUser
public class CinemaControllerTest extends TestContainerBase {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CinemaRepository repository;

    private Cinema templateCinema;

    @BeforeEach
    public void setUp() {
        Address address = new Address();
        address.setCity("City");
        address.setStreet("Street");
        address.setAddressNumber(12);
        templateCinema = new CinemaBuilder()
                .withName("Multikino")
                .withAddress(address)
                .buildValidCinema();
        templateCinema.setId(1L);
    }

    @Test
    void shouldGetSingleCinema() throws Exception {
        final Long cinemaId = 1L;

        when(repository.findById(cinemaId)).thenReturn(Optional.ofNullable(templateCinema));

        mockMvc.perform(get("/api/cinema/{id}", cinemaId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Math.toIntExact(cinemaId))));
    }
}
