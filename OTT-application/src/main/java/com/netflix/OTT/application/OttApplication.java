package com.netflix.OTT.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OttApplication {

	public static void main(String[] args) {
		SpringApplication.run(OttApplication.class, args);
		System.out.println("Running");
	}
}