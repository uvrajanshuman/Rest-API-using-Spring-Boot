package com.example.rest_api_demo;

import com.example.rest_api_demo.services.impl.BookServicesImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestApiDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RestApiDemoApplication.class, args);
		context.getBean(BookServicesImpl.class).populatedb();
	}

}
