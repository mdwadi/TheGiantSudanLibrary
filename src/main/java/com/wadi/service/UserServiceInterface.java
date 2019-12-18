package com.wadi.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.wadi.dto.UserDto;

public interface UserServiceInterface extends UserDetailsService {

	public UserDto registerService(UserDto dto);
	public List<UserDto> getAllUsers();
	public UserDto modifyUser(String id,UserDto dto);
	public UserDto findUserById(String id);
	public String DeleteUserById(String id);

}
