package com.auth.FundAuth;

import com.auth.FundAuth.Filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FundAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundAuthApplication.class, args);
		System.out.println("Running Auth");
	}

	@Bean
	public FilterRegistrationBean getFilter(){
		FilterRegistrationBean filterReg= new FilterRegistrationBean();
		filterReg.setFilter(new TokenFilter());


		filterReg.addUrlPatterns("/auth/getAllUsers");
		filterReg.addUrlPatterns("/auth/user/*") ;
		filterReg.addUrlPatterns("/auth/updateUser/*");

		return filterReg;
	}

}
