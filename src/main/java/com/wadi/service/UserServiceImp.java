package com.wadi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wadi.bo.Role;
import com.wadi.bo.UserBo;
import com.wadi.bo.addBookBo;
import com.wadi.bo.favoriteBo;
import com.wadi.dao.AuthorRepository;
import com.wadi.dao.RoleRepository;
import com.wadi.dao.UserRepository;
import com.wadi.dao.booksRepository;
import com.wadi.dao.favoriteRepository;
import com.wadi.dto.AddBookDto;
import com.wadi.dto.UserDto;
import com.wadi.share.Utils;

@Service
public class UserServiceImp implements UserServiceInterface {

	@Autowired
	private UserRepository repository;

	@Autowired
	private favoriteRepository favorite;

	@Autowired
	private booksRepository bookRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthorRepository author;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils util;

	@Override
	public UserDto registerService(UserDto dto) {
		// TODO Auto-generated method stub
		if (repository.findUserByEmail(dto.getEmail()) != null)
			throw new RuntimeException("Record already exist");

		UserBo bo = new UserBo();

		BeanUtils.copyProperties(dto, bo);

		bo.setEncryptpassword(bCryptPasswordEncoder.encode(dto.getPassword()));

		String publicUserId = util.generateUserId(30);

		bo.setUserid(publicUserId);
		bo.setRoles(Arrays.asList(roleRepository.findByRole("ROLE_USER")));
		bo = repository.save(bo);

		UserDto userdto = new UserDto();
		BeanUtils.copyProperties(bo, userdto);

		return userdto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserBo userBo = repository.findUserByEmail(email);

		if (userBo == null)
			throw new UsernameNotFoundException(email);

		return new User(userBo.getEmail(), userBo.getEncryptpassword(), getAuthorities(userBo));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(UserBo user) {
		String[] userRoles = user.getRoles().stream().map((role) -> role.getRole()).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub

		List<UserBo> bolist = (List<UserBo>) repository.findAll();

		List<UserDto> dtolist = new ArrayList<>();

		for (UserBo bo : bolist) {
			UserDto dto = new UserDto();

			dto.setUserid(bo.getUserid());
			dto.setFname(bo.getFname());
			dto.setEmail(bo.getEmail());
			dto.setPassword(bo.getEncryptpassword());

			dtolist.add(dto);

		}

		return dtolist;
	}

	@Override
	public UserDto modifyUser(String id, UserDto dto) {
		// TODO Auto-generated method stub
		UserBo bo = repository.findByUserId(id);

		dto.setUserid(bo.getUserid());
		BeanUtils.copyProperties(dto, bo);
		UserBo resbo = repository.save(bo);

		UserDto resdto = new UserDto();
		BeanUtils.copyProperties(resbo, resdto);

		return resdto;
	}

	@Override
	public UserDto findUserById(String id) {
		// TODO Auto-generated method stub
		UserBo resbo = repository.findByUserId(id);

		UserDto resdto = new UserDto();

		BeanUtils.copyProperties(resbo, resdto);

		return resdto;
	}

	@Override
	public String DeleteUserById(String id) {
		// TODO Auto-generated method stub

		UserBo resbo = repository.deleteUserByUserId(id);

		return "Success";
	}

	@Override
	public favoriteBo SavefavoriteBook(String userId, long bookId) {

		UserBo userBo = repository.findByUserId(userId);

		addBookBo bookbo = bookRepository.findById(bookId);

		favoriteBo fbo = new favoriteBo();

		fbo.setUser(userBo);
		fbo.setBook(bookbo);

		favoriteBo resfbo = favorite.save(fbo);
		return resfbo;
	}

	@Override
	public List<AddBookDto> findfavorite(String userId) {
		// TODO Auto-generated method stub

		List<favoriteBo> resfbo = favorite.findByUserUserId(userId);

		List<addBookBo> bookbo = new ArrayList<>();

		for (favoriteBo bo : resfbo) {
			addBookBo resbo = bo.getBook();
			System.out.println(resbo.getId());
			bookbo.add(resbo);
		}

		List<AddBookDto> bookdto = new ArrayList<>();

		for (addBookBo bo : bookbo) {
			AddBookDto dto = new AddBookDto();

			BeanUtils.copyProperties(bo, dto);

			bookdto.add(dto);
		}

		return bookdto;
	}

	@Override
	public String deleteFavorite(String userId, long bookId) {
		// TODO Auto-generated method stub

		favoriteBo resfbo = favorite.findByBookIdAndUserUserId(bookId, userId);

		favorite.delete(resfbo);

		return "Success";
	}

	@Override
	public UserDto getUser(String email) {

		UserBo bo = repository.findUserByEmail(email);

		if (bo == null)
			throw new RuntimeException("Record dosen't exist");

		UserDto resdto = new UserDto();
		BeanUtils.copyProperties(bo, resdto);

		return resdto;
	}

	@Override
	public Role addRole(Role role) {

		Role bo = roleRepository.save(role);

		return bo;
	}

	@Override
	public Role DeleteRole(int id) {

		Role bo = roleRepository.findById(id);
		
		roleRepository.delete(bo);
		
		return bo;
	}

	@Override
	public List<Role> getRole() {

		List<Role> bo = roleRepository.findAll();
		// TODO Auto-generated method stub
		return bo;
	}

}
