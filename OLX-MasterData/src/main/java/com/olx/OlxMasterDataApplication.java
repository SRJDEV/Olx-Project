package com.olx;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableEurekaClient
public class OlxMasterDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxMasterDataApplication.class, args);
	}
	
	
	@Bean
	public Docket getCustomisedDocket() {
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiIfo())
				.select()
				
				.apis(RequestHandlerSelectors.basePackage("com.olx"))
				.paths(PathSelectors.any())
				.build();
				
	}
	
	private ApiInfo getApiIfo() {
		return new ApiInfo(
				"Market API Documentation",
				"Market API Documentatio by Zensar",
				"1.0",
				"zensar.com/termsofservice",
				new Contact("Anand", "http://zensar.com","anand@zensar.com"),
				"GPL",
				"sss.com",
				new ArrayList<VendorExtension>());
		
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
