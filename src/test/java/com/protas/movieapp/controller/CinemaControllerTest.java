package com.protas.movieapp.controller;

import com.protas.movieapp.testcontainers.TestContainerBase;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WithMockUser
public class CinemaControllerTest extends TestContainerBase {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetSingleCinema() throws Exception {
        final Long cinemaId = 1L;

        mockMvc.perform(get("/api/cinema/{id}", cinemaId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(cinemaId)));
    }
}
