package com.Assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@EntityScan(basePackages = "com.Assignment.entity") 
@ComponentScan(basePackages = {"com.Assignment.controller, com.Assignment.service, com.Assignment.repository"})
public class MapDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapDataApplication.class, args);
	}

}
