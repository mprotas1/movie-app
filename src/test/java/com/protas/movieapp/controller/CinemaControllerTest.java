package com.protas.movieapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protas.movieapp.address.Address;
import com.protas.movieapp.cinema.Cinema;
import com.protas.movieapp.cinema.CinemaMapper;
import com.protas.movieapp.config.CinemaRepository;
import com.protas.movieapp.testcontainers.TestContainerBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CinemaControllerTest extends TestContainerBase {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CinemaRepository repository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CinemaMapper cinemaMapper;
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

        MvcResult mvcResult = mockMvc.perform(get("/api/cinema/{id}", cinemaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Math.toIntExact(cinemaId))))
                .andReturn();

        String responseResultString = mvcResult.getResponse().getContentAsString();
        var cinema = objectMapper.readValue(responseResultString, Cinema.class);
        assertNotNull(cinema);
        assertEquals(templateCinema.getAddress().getCity(), cinema.getAddress().getCity());
        assertEquals(templateCinema.getName(), cinema.getName());
    }

    @Test
    void shouldGetAllCinemas() throws Exception {
        Page<Cinema> cinemas = Mockito.mock(Page.class);
        when(repository.findAll(Mockito.mock(Pageable.class))).thenReturn(cinemas);

        mockMvc.perform(get("/api/cinema"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateValidCinema() throws Exception {
        String requestContent = objectMapper.writeValueAsString(cinemaMapper.toDTO(templateCinema));

        when(repository.save(Mockito.mock(Cinema.class))).thenReturn(templateCinema);
        MvcResult result = mockMvc.perform(post("/api/cinema")
                        .content(requestContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        var cinema = objectMapper.readValue(responseAsString, Cinema.class);
        assertNotNull(cinema);
    }
}
