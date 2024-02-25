package com.protas.movieapp;

import com.protas.movieapp.dto.ScreeningDTO;
import com.protas.movieapp.mapper.ScreeningMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {
	@Autowired
	private ScreeningMapper mapper;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
