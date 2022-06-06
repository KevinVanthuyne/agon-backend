package com.kevinvanthuyne.agon_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import java.util.List;

@SpringBootApplication
public class AgonBackendApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgonBackendApplication.class);
    private static final List<String> LOG_PROPERTIES = List.of("spring.jpa.hibernate.ddl-auto",
															   "spring.sql.init.mode",
															   "spring.jpa.defer-datasource-initialization",
															   "spring.datasource.url");

    public static void main(String[] args) {
        SpringApplication.run(AgonBackendApplication.class, args);
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
		LOGGER.info("------------------------------------------------");
        Environment env = event.getApplicationContext().getEnvironment();
		for (String property : LOG_PROPERTIES) {
			LOGGER.info("{} = {}", property, env.getProperty(property));
		}
		LOGGER.info("------------------------------------------------");
    }
}
