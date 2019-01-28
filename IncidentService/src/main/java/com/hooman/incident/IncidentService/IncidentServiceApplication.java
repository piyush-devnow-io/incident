package com.hooman.incident.IncidentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.hooman.incident.*","com.hooman.incident.service.*"})
@EnableJpaRepositories(basePackages = "com.hooman.incident.service.repository")
@EntityScan("com.hooman.incident.entity")   
public class IncidentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentServiceApplication.class, args);
	}

}

