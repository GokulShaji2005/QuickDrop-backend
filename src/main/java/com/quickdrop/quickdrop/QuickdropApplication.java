package com.quickdrop.quickdrop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class QuickdropApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickdropApplication.class, args);
	}

}
