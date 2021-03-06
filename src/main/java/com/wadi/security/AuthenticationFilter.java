package com.wadi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wadi.SpringApplicationContext;
import com.wadi.dto.UserDto;
import com.wadi.service.UserServiceInterface;
import com.wadi.vo.UserLoginVo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {

		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse response)
			throws AuthenticationException {
		try {

			UserLoginVo creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginVo.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * private static Collection<? extends GrantedAuthority> getAuthorities(UserBo
	 * user) { String[] userRoles = user.getRoles().stream().map((role) ->
	 * role.getRole()).toArray(String[]::new); Collection<GrantedAuthority>
	 * authorities = AuthorityUtils.createAuthorityList(userRoles); return
	 * authorities; }
	 */

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String userName = ((User) auth.getPrincipal()).getUsername();

		String token = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		UserServiceInterface userService = (UserServiceInterface) SpringApplicationContext.getBean("userServiceImp");

		UserDto dto = userService.getUser(userName);
		res.addHeader("Access-Control-Allow-Origin", "*");

		/* res.addHeader("Access-Control-Allow-Headers", "Origin, X-Custom-Header, Content-Type, Accept");
		res.addHeader("Access-Control-Allow-Credentials","true");*/
		res.addHeader("Access-Control-Expose-Headers",SecurityConstants.HEADER_STRING +",UserId");
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

		res.addHeader("UserId", dto.getUserid());
		
		
		 
	
	}

}
