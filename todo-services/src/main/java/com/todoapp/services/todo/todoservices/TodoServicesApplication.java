package com.todoapp.services.todo.todoservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.todoapp.services.util.utilservices") // required to use a bean from another module
public class TodoServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoServicesApplication.class, args);
	}
}
