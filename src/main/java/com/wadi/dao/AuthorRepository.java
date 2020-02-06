package com.wadi.dao;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wadi.bo.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Id> {
	
	
	Author findByNameAr(String nameAr);

	Author findByNameEn(String nameEn);

}
