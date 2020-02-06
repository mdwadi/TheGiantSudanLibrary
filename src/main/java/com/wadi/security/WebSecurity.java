package com.wadi.security;

import java.security.Signature;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

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
		http.cors().and().authorizeRequests()
		.antMatchers(HttpMethod.GET,"/bookList").permitAll()
		.antMatchers(HttpMethod.GET,"/AddBook").permitAll()
		.antMatchers(HttpMethod.POST,"/AddBook").permitAll()
		.antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL).permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(getAuthenticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf().disable();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		
		final AuthenticationFilter filter=new AuthenticationFilter(authenticationManager());
		
		filter.setFilterProcessesUrl("/user/login");
		
		return filter;
	}
	
}
