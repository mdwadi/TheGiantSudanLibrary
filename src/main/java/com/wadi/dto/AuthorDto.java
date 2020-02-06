package com.wadi.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class AuthorDto {


	private long id;
	

	private String nameEn;
	
	private String nameAr;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getNameAr() {
		return nameAr;
	}
	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}
}
