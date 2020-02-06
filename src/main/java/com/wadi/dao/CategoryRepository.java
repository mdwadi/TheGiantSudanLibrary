package com.wadi.dao;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wadi.bo.Category;
import com.wadi.bo.addBookBo;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Id> {


}
