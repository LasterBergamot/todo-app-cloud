package com.todoapp.services.user.userservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan(basePackages = "com.todoapp.services.util.utilservices") // required to use a bean from another module
@ComponentScan(basePackages = "com.todoapp.services.user.userservices")
public class UserServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserServicesApplication.class, args);
	}
}
