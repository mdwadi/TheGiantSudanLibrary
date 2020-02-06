package com.wadi.dao;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wadi.bo.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Id> {

	Role findByRole(String role);

	Role findById(int id);

}
