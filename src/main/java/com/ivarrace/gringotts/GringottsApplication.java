package com.ivarrace.gringotts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class GringottsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GringottsApplication.class, args);
	}

}
