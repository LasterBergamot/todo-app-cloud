package com.todoapp.webapp.todoappwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TodoAppWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppWebappApplication.class, args);
	}

}
