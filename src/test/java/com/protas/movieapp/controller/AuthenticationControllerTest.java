package com.protas.movieapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protas.movieapp.user.Role;
import com.protas.movieapp.user.User;
import com.protas.movieapp.user.AuthenticationResponse;
import com.protas.movieapp.user.RoleType;
import com.protas.movieapp.user.UserRepository;
import com.protas.movieapp.testcontainers.TestContainerBase;
import com.protas.movieapp.utils.jwt.JwtUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest extends TestContainerBase {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ObjectMapper mapper;
    @Mock
    UserRepository userRepository;
    private JSONObject registerUserCredentialsJson;
    private JSONObject authenticateUserCredentialsJson;
    private final String REGISTER_ENDPOINT = "/api/auth/register";
    private final String AUTHENTICATION_ENDPOINT = "/api/auth/authenticate";

    @BeforeEach
    void setUpBeforeEach() throws JSONException {
        registerUserCredentialsJson = new JSONObject();
        registerUserCredentialsJson.put("email", "mprotas@gmail.com");
        registerUserCredentialsJson.put("username", "username");
        registerUserCredentialsJson.put("password", "password123");

        authenticateUserCredentialsJson = new JSONObject();
        authenticateUserCredentialsJson.put("email", "mprotas@gmail.com");
        authenticateUserCredentialsJson.put("username", "username");
        authenticateUserCredentialsJson.put("password", "password123");
    }

    @Test
    void shouldRegisterUserWithValidCredentials() throws Exception {
        MvcResult result = mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerUserCredentialsJson.toString())
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
        registerUserCredentialsJson.put("email", "");
        MvcResult mvcResult = mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerUserCredentialsJson.toString())
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
        registerUserCredentialsJson.put("email", "invalid-email-format");

        mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerUserCredentialsJson.toString()))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.errors[0].field").value("email"))
                .andExpect(jsonPath("$.errors[0].message").value("must be a well-formed email address"));
    }

    @Test
    void shouldNotRegisterUserWithBlankPassword() throws Exception {
        registerUserCredentialsJson.put("password", "");

        MvcResult mvcResult = mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerUserCredentialsJson.toString())
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
    void shouldNotRegisterUserWithBlankUsername() throws Exception {
        registerUserCredentialsJson.put("username", "");

        MvcResult mvcResult = mockMvc.perform(post(REGISTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerUserCredentialsJson.toString())
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
    void shouldAuthenticateExistingUser() throws Exception {
        User temporaryUser = User.builder().withUsername("mprotas")
                .withEmail("mprotas@gmail.com")
                .withPassword("password")
                .withRoles(Set.of(new Role().withRoleType(RoleType.USER)))
                .build();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(temporaryUser));

        MvcResult mvcResult = mockMvc.perform(post(AUTHENTICATION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(authenticateUserCredentialsJson.toString())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        AuthenticationResponse response = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                AuthenticationResponse.class);

        assertNotNull(response.token());
    }


}
