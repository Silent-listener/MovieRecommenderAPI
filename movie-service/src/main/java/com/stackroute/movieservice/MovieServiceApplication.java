package com.stackroute.movieservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class MovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}
}
