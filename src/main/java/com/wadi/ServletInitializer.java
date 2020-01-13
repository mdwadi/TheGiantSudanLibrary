package com.wadi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ServletInitializer extends SpringBootServletInitializer implements WebMvcConfigurer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TheGiantSudanLibraryApplication.class);
	}
	
	@Override
	 public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
     }

}
