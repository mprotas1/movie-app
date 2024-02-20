package com.protas.movieapp.service.screening;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(value = "/application-test.properties")
public class ScreeningCreateServiceTest {
}
