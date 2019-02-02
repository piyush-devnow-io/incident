package com.hooman.incident.IncidentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.hooman.incident.*","com.hooman.incident.service.*"})
@EnableJpaRepositories(basePackages = "com.hooman.incident.service.repository")
@EntityScan("com.hooman.incident.entity")  
@PropertySource(value = "file:/root/incidentservice/application.properties", ignoreResourceNotFound = false)
public class IncidentServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(IncidentServiceApplication.class);
	public static void main(String[] args) {
		logger.info("starting incident application");
		SpringApplication.run(IncidentServiceApplication.class, args);
		logger.info("incident service started	");
	}

}

