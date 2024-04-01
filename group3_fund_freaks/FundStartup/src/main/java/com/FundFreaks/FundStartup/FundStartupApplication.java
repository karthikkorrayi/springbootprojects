package com.FundFreaks.FundStartup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundStartupApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundStartupApplication.class, args);
		System.out.println("Running the Startup Server");
	}
}
