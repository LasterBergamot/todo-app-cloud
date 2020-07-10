package com.todoapp.services.todo.todoservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TodoServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoServicesApplication.class, args);
	}

}
