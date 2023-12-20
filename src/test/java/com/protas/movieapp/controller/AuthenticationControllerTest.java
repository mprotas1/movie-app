package com.protas.movieapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protas.movieapp.model.AuthenticationResponse;
import com.protas.movieapp.testcontainers.TestContainerBase;
import com.protas.movieapp.utils.JwtUtils;
import jakarta.validation.ConstraintViolationException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WithAnonymousUser
public class AuthenticationControllerTest extends TestContainerBase {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ObjectMapper mapper;

    JSONObject userCredentialsJson;

    @BeforeEach
    void setUpBeforeAll() throws JSONException {
        userCredentialsJson = new JSONObject();
        userCredentialsJson.put("email", "mprotas@gmail.com");
        userCredentialsJson.put("username", "username");
        userCredentialsJson.put("password", "password123");
    }

    @Test
    void shouldRegisterUserWithValidCredentials() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCredentialsJson.toString())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        AuthenticationResponse response = mapper.readValue(result.getResponse()
                .getContentAsString(), AuthenticationResponse.class);

        assertNotNull(response);
        assertFalse(jwtUtils.isTokenExpired(response.token()));
    }

    @Test
    void shouldNotRegisterUserWithEmptyEmailField() throws Exception {
        userCredentialsJson.put("email", "");
        MvcResult mvcResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCredentialsJson.toString())
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andReturn();

        AuthenticationResponse response = mapper.readValue(mvcResult.getResponse()
                .getContentAsString(), AuthenticationResponse.class);

        assertNull(response.token());
    }

    @Test
    void shouldNotRegisterUserWithMalformedPatternOfEmail() throws Exception {
        userCredentialsJson.put("email", "mprotas.gmail.con");
        MvcResult mvcResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCredentialsJson.toString())
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andReturn();

        AuthenticationResponse response = mapper.readValue(mvcResult.getResponse()
                .getContentAsString(), AuthenticationResponse.class);

        System.out.println(response);

        assertNull(response.token());
    }

}
