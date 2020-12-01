package com.apap.joblist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
@SpringBootApplication
public class JobListApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobListApplication.class, args);
	}

}
