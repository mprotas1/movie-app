package com.protas.movieapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protas.movieapp.dto.DirectorDTO;
import com.protas.movieapp.entity.movie.Director;
import com.protas.movieapp.repository.DirectorRepository;
import com.protas.movieapp.service.director.DirectorCreateService;
import com.protas.movieapp.testcontainers.TestContainerBase;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WithMockUser
public class DirectorControllerTest extends TestContainerBase {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DirectorRepository repository;

    @MockBean
    DirectorCreateService directorCreateService;

    @Autowired
    ObjectMapper objectMapper;

    private Director director;

    @BeforeEach
    public void setUp() {
        director = new Director();
        director.setId(1L);
        director.setFirstName("James");
        director.setLastName("Cameron");
    }

    @Test
    public void shouldFindDirectorById() throws Exception {
        final Long directorId = 1L;
        when(repository.findById(directorId)).thenReturn(Optional.ofNullable(director));

        var result = mockMvc.perform(get("/api/director/{id}", directorId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(Math.toIntExact(directorId))))
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        assertEquals(director, objectMapper.readValue(responseString, Director.class));
    }

    @Test
    public void shouldNotFindDirectorWithWrongId() throws Exception {
        final Long directorId = 999L;
        when(repository.findById(directorId)).thenThrow(new EntityNotFoundException());

        var result = mockMvc.perform(get("/api/director/{id}", directorId))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(result.getResolvedException() instanceof EntityNotFoundException);
    }

    @Test
    public void shouldCreateDirectorWithProperData() throws Exception {
        DirectorDTO dto = new DirectorDTO("James", "Cameron");
        when(directorCreateService.create(dto)).thenReturn(director);

        var result = mockMvc.perform(post("/api/director")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(Math.toIntExact(director.getId()))))
                .andReturn();

        Director createdDirector = objectMapper.readValue(result.getResponse().getContentAsString(), Director.class);
        assertNotNull(createdDirector);
    }

    @Test
    public void shouldNotCreateDirectorWithInappropriateData() throws Exception {
        DirectorDTO dto = new DirectorDTO(null, "");
        String jsonDto = objectMapper.writeValueAsString(dto);

        var result = mockMvc.perform(post("/api/director")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonDto))
                .andExpect(status().isBadRequest())
                .andReturn();

        // TODO: expect RestExceptionMessage to be displayed
        System.out.println(result.getResponse().getContentAsString());
    }

}
