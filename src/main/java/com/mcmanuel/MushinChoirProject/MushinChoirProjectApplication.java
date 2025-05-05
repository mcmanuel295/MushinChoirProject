package com.mcmanuel.MushinChoirProject;

import com.mcmanuel.MushinChoirProject.repository.UserRepository;
import com.mcmanuel.MushinChoirProject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.UUID;

@SpringBootApplication
@EnableAsync
@EnableWebSecurity
@EnableMethodSecurity
public class MushinChoirProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MushinChoirProjectApplication.class, args);
	}



}