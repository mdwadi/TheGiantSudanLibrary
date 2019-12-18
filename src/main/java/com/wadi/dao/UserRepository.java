package com.wadi.dao;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.wadi.bo.UserBo;

public interface UserRepository extends CrudRepository<UserBo, Long> {

	UserBo findUserByEmail(String email);

	UserBo findByUserId(String id);

	UserBo deleteUserById(String id);

}
