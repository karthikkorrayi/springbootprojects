package com.investment.fundInvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundInvestApplication {
	public static void main(String[] args) {
		SpringApplication.run(FundInvestApplication.class, args);
		System.out.println("Running the investor");
	}
}
