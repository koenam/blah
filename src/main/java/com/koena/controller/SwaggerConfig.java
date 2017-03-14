package com.koena.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

   /**
    * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple
    * swagger groups i.e. same code base multiple swagger resource listings.
    */
	
	//http://localhost:8080/blah/swagger-ui.html#/rest-public-holiday-controller
	 @Bean
	    public Docket newsApi() {

	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("Public Holidays")
	                .apiInfo(apiInfo())
	                .select()
	                 .paths(PathSelectors.regex("/.*"))
	                .build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Public Holidays REST Service")
	                .description("Public Holidays REST Service")
	                .contact("Koena Mangena")
	                .version("2.0")
				.build();
	}

}