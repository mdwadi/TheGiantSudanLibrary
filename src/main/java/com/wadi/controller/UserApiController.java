package com.wadi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wadi.bo.addBookBo;
import com.wadi.dto.AddBookDto;
import com.wadi.dto.UserDto;
import com.wadi.response.UserResponse;
import com.wadi.service.UserServiceInterface;
import com.wadi.service.addBooksServiceInt;
import com.wadi.vo.UserVo;

@RestController
public class UserApiController {

	@Autowired
	private UserServiceInterface service;
	@Autowired
	private addBooksServiceInt bookService;

	@PostMapping("/adduser")
	public UserResponse registerUser(@RequestBody UserVo vo) {

		UserDto userData = new UserDto();

		BeanUtils.copyProperties(vo, userData);

		UserDto uservalue = service.registerService(userData);

		UserResponse resValue = new UserResponse();
		BeanUtils.copyProperties(uservalue, resValue);
		return resValue;
	}

	@GetMapping("/userlist")
	public List<UserResponse> getUsesList() {
		List<UserDto> listDto = service.getAllUsers();

		List<UserResponse> resValue = new ArrayList<>();
		for (UserDto dto : listDto) {
			UserResponse res = new UserResponse();
			BeanUtils.copyProperties(dto, res);

			resValue.add(res);
		}

		return resValue;

	}

	@GetMapping("/user/{id}")
	public UserResponse getUsesById(@PathVariable String id) {

		UserDto resdto = service.findUserById(id);

		UserResponse resUser = new UserResponse();

		BeanUtils.copyProperties(resdto, resUser);

		return resUser;

	}

	@PutMapping("/modifyUser/{id}")
	public UserResponse modifyUser(@PathVariable String id, @RequestBody UserVo vo) {

		UserDto dto = new UserDto();
		BeanUtils.copyProperties(vo, dto);

		UserDto resdto = service.modifyUser(id, dto);

		UserResponse resUser = new UserResponse();

		BeanUtils.copyProperties(resdto, resUser);

		return resUser;

	}

	@DeleteMapping("/delete/{id}")
	public String DeleteUser(@PathVariable String id) {

		String resdto = service.DeleteUserById(id);

		return resdto;

	}

	@PutMapping("/user/{userId}/favorite/{url}")
	public UserResponse addfavorite(@PathVariable(value = "userId") String id, @PathVariable(value = "url") long url) {

		AddBookDto dto = bookService.findBookByid(url);
		addBookBo bo = new addBookBo();

		BeanUtils.copyProperties(dto, bo);

		UserDto resdto = service.findUserById(id);
		resdto.getAddBookBo().add(bo);

		UserDto uservalue = service.registerService(resdto);

		UserResponse resValue = new UserResponse();
		BeanUtils.copyProperties(uservalue, resValue);
		return resValue;

	}

}
