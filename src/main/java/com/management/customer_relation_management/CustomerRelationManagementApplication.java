package com.management.customer_relation_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CustomerRelationManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRelationManagementApplication.class, args);
	}

}
