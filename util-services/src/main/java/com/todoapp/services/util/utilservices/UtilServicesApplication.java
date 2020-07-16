package com.todoapp.services.util.utilservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UtilServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilServicesApplication.class, args);
	}

}
