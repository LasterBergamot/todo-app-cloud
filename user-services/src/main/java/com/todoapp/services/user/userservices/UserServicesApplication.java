package com.todoapp.services.user.userservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.todoapp.services.util.utilservices")
public class UserServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserServicesApplication.class, args);
	}
}
