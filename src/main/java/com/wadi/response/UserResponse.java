package com.wadi.response;

import java.util.Set;

import com.wadi.bo.addBookBo;

public class UserResponse {
	
	private String userid;
	private String fname;
	private String email;
	private Set<addBookBo> addBookBo;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<addBookBo> getAddBookBo() {
		return addBookBo;
	}

	public void setAddBookBo(Set<addBookBo> addBookBo) {
		this.addBookBo = addBookBo;
	}

}
