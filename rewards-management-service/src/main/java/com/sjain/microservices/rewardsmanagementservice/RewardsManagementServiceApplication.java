package com.sjain.microservices.rewardsmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RewardsManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsManagementServiceApplication.class, args);
	}

}
