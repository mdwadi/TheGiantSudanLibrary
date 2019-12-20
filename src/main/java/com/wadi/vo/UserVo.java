package com.wadi.vo;

import java.util.Set;

import com.wadi.bo.addBookBo;

public class UserVo {
	
	private String fname;
	private String lname;
	private String email;
	private String password;
	private Set<addBookBo> addBookBo;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<addBookBo> getAddBookBo() {
		return addBookBo;
	}

	public void setAddBookBo(Set<addBookBo> addBookBo) {
		this.addBookBo = addBookBo;
	}
	
	

	

}
