package com.cognizant.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class swaggerConfiguration {
	
	 @Bean
	    public InternalResourceViewResolver defaultViewResolver() {
	        return new InternalResourceViewResolver();
	    }
	 
	 @Bean
	 public Docket getDocket() {
		 return new Docket(DocumentationType.SWAGGER_2)
				 .select()
				 .apis(RequestHandlerSelectors.basePackage("com.cognizant"))
				 .build()
				 .apiInfo(getInfo());
	 }

	private ApiInfo getInfo() {
		return new ApiInfoBuilder().title("Contact Management system")
				.description("This project is for Learning Rest developed")
				.version("1.0")
				.contact(new Contact("Shubham","shubham","shubhamanand190@gmail.com"))
				.build();
	}
	
	


}
