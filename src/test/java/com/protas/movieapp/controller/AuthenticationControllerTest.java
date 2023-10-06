package com.protas.movieapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protas.movieapp.model.AuthenticationResponse;
import com.protas.movieapp.service.UserAuthService;
import com.protas.movieapp.utils.JwtUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = AuthenticationController.class)
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    TestRestTemplate restTemplate;
    @MockBean
    UserAuthService authService;
    @MockBean
    JwtUtils jwtUtils;
    @Mock
    ObjectMapper objectMapper;

    private final int PORT = 8080;

    @Test
    @DisplayName(value = "Should register User with valid RegisterRequestDTO")
    void shouldRegisterUserWithValidCredentials() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "mprotas@gmail.com");
        jsonObject.put("username", "username");
        jsonObject.put("password", "password123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);

        System.out.println("HttpEntity: " + request.toString());

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity("/api/auth/register", request, String.class);

        System.out.println(responseEntity.getBody());
    }

}
