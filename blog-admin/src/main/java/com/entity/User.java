package com.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String userPassword;
	private String userRealityName;
	private Set mails = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String userPassword, String userRealityName) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRealityName = userRealityName;
	}

	/** full constructor */
	public User(String userName, String userPassword, String userRealityName, Set mails) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRealityName = userRealityName;
		this.mails = mails;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRealityName() {
		return this.userRealityName;
	}

	public void setUserRealityName(String userRealityName) {
		this.userRealityName = userRealityName;
	}

	public Set getMails() {
		return this.mails;
	}

	public void setMails(Set mails) {
		this.mails = mails;
	}

}