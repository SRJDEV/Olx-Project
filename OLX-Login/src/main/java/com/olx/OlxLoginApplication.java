package com.olx;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
public class OlxLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxLoginApplication.class, args);
	}

	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 }
		
		@Bean
		public ModelMapper getModelMapper() {
			return new ModelMapper();
		}
}
