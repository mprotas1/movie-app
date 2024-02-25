package com.protas.movieapp.service.screening;

import com.protas.movieapp.dto.ScreeningDTO;
import com.protas.movieapp.mapper.ScreeningMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(value = "/application-test.properties")
public class ScreeningCreateServiceTest {
    @Autowired
    ScreeningMapper mapper;
    @Test
    void shouldCreateSingleValidScreening() {
        var screening = mapper.fromDTO(new ScreeningDTO(1L, 1L, LocalDateTime.now()));
    }
}
