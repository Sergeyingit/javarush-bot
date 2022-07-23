package com.github.sergeyingit.javarushbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JavarushBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavarushBotApplication.class, args);
	}

}
