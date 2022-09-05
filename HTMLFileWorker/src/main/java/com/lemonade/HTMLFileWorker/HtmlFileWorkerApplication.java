package com.lemonade.HTMLFileWorker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class HtmlFileWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtmlFileWorkerApplication.class, args);
	}

}
