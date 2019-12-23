package com.wadi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wadi.bo.favoriteBo;

@Repository
public interface favoriteRepository extends CrudRepository<favoriteBo, Long> {

	List<favoriteBo> findByUserUserId(String userId);

	favoriteBo findByBookId(Long id);

	favoriteBo findByBookIdAndUserUserId(long bookId, String userId);
}
