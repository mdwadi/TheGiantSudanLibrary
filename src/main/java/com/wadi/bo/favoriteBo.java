package com.wadi.bo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "favorite")
public class favoriteBo {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	private UserBo user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	private addBookBo book;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserBo getUser() {
		return user;
	}

	public void setUser(UserBo user) {
		this.user = user;
	}

	public addBookBo getBook() {
		return book;
	}

	public void setBook(addBookBo book) {
		this.book = book;
	}

}
