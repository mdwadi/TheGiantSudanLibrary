package com.wadi.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	private int id;
	

	@Column(nullable = false, length = 50)
	private String categoryEn;
	

	@Column(nullable = false, length = 50)
	private String categoryAr;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryEn() {
		return categoryEn;
	}
	public void setCategoryEn(String categoryEn) {
		this.categoryEn = categoryEn;
	}
	public String getCategoryAr() {
		return categoryAr;
	}
	public void setCategoryAr(String categoryAr) {
		this.categoryAr = categoryAr;
	}

}
