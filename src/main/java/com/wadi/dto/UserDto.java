package com.wadi.dto;

import java.io.Serializable;
import java.util.Set;

import com.wadi.bo.addBookBo;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 2184987153467588527L;
	private long id;
	private String userid;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String encryptpassword;
	private String emailVerificationTokent;
	private Boolean emailVerificationStatus = false;
	private Set<addBookBo> addBookBo;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptpassword() {
		return encryptpassword;
	}

	public void setEncryptpassword(String encryptpassword) {
		this.encryptpassword = encryptpassword;
	}

	public String getEmailVerificationTokent() {
		return emailVerificationTokent;
	}

	public void setEmailVerificationTokent(String emailVerificationTokent) {
		this.emailVerificationTokent = emailVerificationTokent;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public Set<addBookBo> getAddBookBo() {
		return addBookBo;
	}

	public void setAddBookBo(Set<addBookBo> addBookBo) {
		this.addBookBo = addBookBo;
	}

}
