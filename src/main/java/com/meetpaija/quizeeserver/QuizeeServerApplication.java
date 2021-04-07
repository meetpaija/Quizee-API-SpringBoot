package com.meetpaija.quizeeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class QuizeeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizeeServerApplication.class, args);
	}

}
