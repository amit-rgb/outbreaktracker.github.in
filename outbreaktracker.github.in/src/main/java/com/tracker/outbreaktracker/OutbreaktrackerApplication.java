package com.tracker.outbreaktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OutbreaktrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutbreaktrackerApplication.class, args);
	}

}
