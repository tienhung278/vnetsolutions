package com.awaydigitalteams.vnetsolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VnetsolutionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VnetsolutionsApplication.class, args);
	}
}
