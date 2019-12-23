package com.wadi.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.wadi.bo.favoriteBo;
import com.wadi.dto.AddBookDto;
import com.wadi.dto.UserDto;

public interface UserServiceInterface extends UserDetailsService {

	public UserDto registerService(UserDto dto);
	public List<UserDto> getAllUsers();
	public UserDto modifyUser(String id,UserDto dto);
	public UserDto findUserById(String id);
	public String DeleteUserById(String id);
	public favoriteBo SavefavoriteBook(String userId,long bookId);
	public List<AddBookDto> findfavorite(String userId);
	public String deleteFavorite(String userId,long bookId);

}
