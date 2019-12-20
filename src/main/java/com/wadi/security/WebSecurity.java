package com.wadi.security;

import java.security.Signature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wadi.service.UserServiceInterface;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceInterface userDetailsService;
	
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserServiceInterface userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.userDetailsService=userDetailsService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL).permitAll()
		.anyRequest().permitAll();/*authenticated().and().addFilter(new AuthenticationFilter(authenticationManager()));
	*/}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
}
