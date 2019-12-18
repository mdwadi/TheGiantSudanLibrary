package com.wadi.dao;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wadi.bo.addBookBo;

@Repository
public interface booksRepository extends JpaRepository<addBookBo, Id> {

	addBookBo findById(long id);

	String deleteById(long id);

	addBookBo findByBookUrl(String bookurl);

}
