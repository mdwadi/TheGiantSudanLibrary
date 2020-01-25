package com.wadi.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {

	@Bean("localeResolver")
	public LocaleResolver localeResolver() {

		System.out.println("WebAppConfig.localeResolver()");

		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor LocaleChangeInterceptor() {

		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");

		return lci;

	}

	/*
	 * @Bean("messageSource") public ResourceBundleMessageSource messageSouce() {
	 * ResourceBundleMessageSource messagesource = new
	 * ResourceBundleMessageSource(); messagesource.setBasename("/messages");
	 * //messagesource.setDefaultEncoding("UTF-8");
	 * 
	 * return messagesource; }
	 */
	
	 @Override
     public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                 .allowedHeaders("*");
     }
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		System.out.println("WebAppConfig.addInterceptors()");
		
		registry.addInterceptor(LocaleChangeInterceptor());
		
	}
	 
	 
	
}
