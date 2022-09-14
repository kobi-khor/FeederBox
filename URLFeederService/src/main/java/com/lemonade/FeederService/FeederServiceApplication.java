package com.lemonade.FeederService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.lemonade.FeederService.repository")
public class FeederServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeederServiceApplication.class, args);
	}

}
