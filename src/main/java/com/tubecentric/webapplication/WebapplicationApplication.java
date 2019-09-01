package com.tubecentric.webapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages={"com.tubecentric"})
public class WebapplicationApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebapplicationApplication.class, args);
	}

}
