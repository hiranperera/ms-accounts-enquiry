package com.anz.ms.accountenquiry;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition (info = @Info(
		title = "Account Transaction Enquiry Service",
		version = "1.0",
		description = "This is the account and transaction retrieval microservice."))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}